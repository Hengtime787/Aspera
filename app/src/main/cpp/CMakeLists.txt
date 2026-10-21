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
)

target_include_directories(
        aspera_native
        PRIVATE
        ${CMAKE_CURRENT_SOURCE_DIR}/fasttext
)

find_library(
        log-lib
        log
)

target_link_libraries(
        aspera_native
        ${log-lib}
)
