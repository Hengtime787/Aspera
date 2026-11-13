# AI GENERATED FILE // ANDROID STUDIO GEMINI WAS
cmake_minimum_required(VERSION 3.22.1)

project(aspera)

# Enable maximum release optimizations (-O3, LTO, SIMD)
set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -O3 -flto -march=armv8-a+simd")
set(CMAKE_C_FLAGS_RELEASE "${CMAKE_C_FLAGS_RELEASE} -O3 -flto -march=armv8-a+simd")

add_library(
        aspera_native
        SHARED
        Adrenaline.cpp
        Mask.cpp
        crf_stub.c
        fasttext/args.cc
        fasttext/densematrix.cc
        fasttext/dictionary.cc
        fasttext/fasttext.cc
        fasttext/loss.cc
        fasttext/matrix.cc
        fasttext/model.cc
        fasttext/productquantizer.cc
        fasttext/quantmatrix.cc
        fasttext/utils.cc
        fasttext/vector.cc
        crfsuite/lib/cqdb/src/cqdb.c
        crfsuite/lib/cqdb/src/lookup3.c
        crfsuite/lib/crf/src/crfsuite.c
        crfsuite/lib/crf/src/crf1d_context.c
        crfsuite/lib/crf/src/crf1d_encode.c
        crfsuite/lib/crf/src/crf1d_feature.c
        crfsuite/lib/crf/src/crf1d_model.c
        crfsuite/lib/crf/src/crf1d_tag.c
        crfsuite/lib/crf/src/dataset.c
        crfsuite/lib/crf/src/dictionary.c
        crfsuite/lib/crf/src/holdout.c
        crfsuite/lib/crf/src/logging.c
        crfsuite/lib/crf/src/params.c
        crfsuite/lib/crf/src/quark.c
        crfsuite/lib/crf/src/rumavl.c
)

target_include_directories(
        aspera_native
        PRIVATE
        ${CMAKE_CURRENT_SOURCE_DIR}/fasttext
        ${CMAKE_CURRENT_SOURCE_DIR}/crfsuite/include
        ${CMAKE_CURRENT_SOURCE_DIR}/crfsuite/lib/cqdb/include
        ${CMAKE_CURRENT_SOURCE_DIR}/crfsuite/lib/crf/src
)

find_library(
        log-lib
        log
)

target_link_libraries(
        aspera_native
        ${log-lib}
)
