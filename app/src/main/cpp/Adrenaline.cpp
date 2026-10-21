// AI GENERATED FILE // ANDROID STUDIO GEMINI WAS USED FOR MOST TEXT IN FILE //
#include <jni.h>
#include <string>
#include <sstream>
#include <vector>
#include <memory>
#include <mutex>
#include <android/log.h>
#include "fasttext/fasttext.h"

#define TAG "AdrenalineJNI"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

static std::unique_ptr<fasttext::FastText> g_fastText = nullptr;
static std::mutex g_mutex;

extern "C" JNIEXPORT jboolean JNICALL
Java_com_aspera_space_Adrenaline_Ignite(
        JNIEnv* env,
        jobject thiz,
        jstring modelPath_) {
    std::lock_guard<std::mutex> lock(g_mutex);

    if (g_fastText != nullptr) {
        LOGD("FastText model already ignited.");
        return JNI_TRUE;
    }

    const char* modelPath = env->GetStringUTFChars(modelPath_, nullptr);
    if (modelPath == nullptr) {
        LOGE("Failed to get model path string.");
        return JNI_FALSE;
    }

    try {
        auto ft = std::make_unique<fasttext::FastText>();
        ft->loadModel(modelPath);
        g_fastText = std::move(ft);
        env->ReleaseStringUTFChars(modelPath_, modelPath);
        LOGD("FastText model successfully ignited.");
        return JNI_TRUE;
    } catch (const std::exception& e) {
        LOGE("Exception igniting FastText model: %s", e.what());
        env->ReleaseStringUTFChars(modelPath_, modelPath);
        return JNI_FALSE;
    }
}

extern "C" JNIEXPORT void JNICALL
Java_com_aspera_space_Adrenaline_Purge(
        JNIEnv* env,
        jobject thiz) {
    std::lock_guard<std::mutex> lock(g_mutex);
    g_fastText.reset();
    LOGD("FastText model purged, RAM freed.");
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_aspera_space_Adrenaline_Resolve(
        JNIEnv* env,
        jobject thiz,
        jstring query_) {
    std::lock_guard<std::mutex> lock(g_mutex);

    if (g_fastText == nullptr) {
        LOGE("FastText model not ignited!");
        return env->NewStringUTF("__label__UNKNOWN 0.0");
    }

    const char* query = env->GetStringUTFChars(query_, nullptr);
    if (query == nullptr) {
        return env->NewStringUTF("__label__UNKNOWN 0.0");
    }

    std::string text(query);
    env->ReleaseStringUTFChars(query_, query);

    try {
        LOGD("Query received in C++: %s", text.c_str());

        // FastText getLine expects a newline terminator in the stream
        std::istringstream iRow(text + "\n");
        std::vector<std::pair<fasttext::real, std::string>> predictions;

        g_fastText->predictLine(iRow, predictions, 1, 0.0);

        if (predictions.empty()) {
            LOGE("Predictions empty!");
            return env->NewStringUTF("__label__UNKNOWN 0.0");
        }

        const auto& pred = predictions[0];
        LOGD("FastText predicted label: %s with score: %f", pred.second.c_str(), pred.first);

        std::string result = pred.second + " " + std::to_string(pred.first);
        return env->NewStringUTF(result.c_str());
    } catch (const std::exception& e) {
        LOGE("Prediction exception: %s", e.what());
        return env->NewStringUTF("ERROR: EXCEPTION");
    }
}
