//EH THIS ONE IS ALMOST ALL AI/. GEMINI ANDROID STUDIO AGENT../
// Created by Matthew* on 11/13/26.
//

#include <jni.h>
#include <string>
#include <vector>
#include <memory>
#include <mutex>
#include <sstream>
#include <android/log.h>
#include "crfsuite.hpp"

#define TAG "MaskJNI"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

static std::unique_ptr<CRFSuite::Tagger> g_tagger = nullptr;
static std::mutex g_crf_mutex;

extern "C" JNIEXPORT jboolean JNICALL
Java_com_aspera_space_Mask_Ignite(
        JNIEnv* env,
        jobject thiz,
        jstring modelPath_) {

    std::lock_guard<std::mutex> lock(g_crf_mutex);

    if (g_tagger != nullptr) {
        LOGD("CRFsuite model already ignited.");
        return JNI_TRUE;
    }

    const char* modelPath = env->GetStringUTFChars(modelPath_, nullptr);
    if (modelPath == nullptr) {
        LOGE("Failed to get CRF model path string.");
        return JNI_FALSE;
    }

    try {
        auto tagger = std::make_unique<CRFSuite::Tagger>();
        if (!tagger->open(modelPath)) {
            LOGE("Failed to open CRF model at: %s", modelPath);
            env->ReleaseStringUTFChars(modelPath_, modelPath);
            return JNI_FALSE;
        }
        g_tagger = std::move(tagger);
        env->ReleaseStringUTFChars(modelPath_, modelPath);
        LOGD("CRFsuite model successfully ignited.");
        return JNI_TRUE;
    } catch (const std::exception& e) {
        LOGE("Exception igniting CRFsuite model: %s", e.what());
        env->ReleaseStringUTFChars(modelPath_, modelPath);
        return JNI_FALSE;
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_aspera_space_Mask_Purge(
        JNIEnv* env,
        jobject thiz) {
    std::lock_guard<std::mutex> lock(g_crf_mutex);
    g_tagger.reset();
    LOGD("CRFsuite model purged, RAM freed.");
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_aspera_space_Mask_Resolve(
        JNIEnv* env,
        jobject thiz,
        jstring query_) {
    std::lock_guard<std::mutex> lock(g_crf_mutex);

    if (g_tagger == nullptr) {
        LOGE("CRFsuite model not ignited!");
        return env->NewStringUTF("UNKNOWN");
    }

    const char* query = env->GetStringUTFChars(query_, nullptr);
    if (query == nullptr) {
        return env->NewStringUTF("UNKNOWN");
    }

    std::string text(query);
    env->ReleaseStringUTFChars(query_, query);

    try {
        LOGD("CRF Query received in C++: %s", text.c_str());

        CRFSuite::ItemSequence xseq;
        std::istringstream stream(text);
        std::string token;
        while (stream >> token) {
            if (token.empty()) continue;
            CRFSuite::Item item;
            CRFSuite::Attribute attribute(token, 1.0);
            item.push_back(attribute);
            xseq.push_back(item);
        }

        if (xseq.empty()) {
            return env->NewStringUTF("EMPTY_SEQUENCE");
        }

        CRFSuite::StringList labels = g_tagger->tag(xseq);
        if (labels.empty()) {
            return env->NewStringUTF("UNKNOWN");
        }

        std::string result;
        for (size_t i = 0; i < labels.size(); ++i) {
            if (i > 0) result += ' ';
            result += labels[i];
        }

        return env->NewStringUTF(result.c_str());
    } catch (const std::exception& e) {
        LOGE("CRF Prediction exception: %s", e.what());
        return env->NewStringUTF("ERROR: EXCEPTION");
    }
}
