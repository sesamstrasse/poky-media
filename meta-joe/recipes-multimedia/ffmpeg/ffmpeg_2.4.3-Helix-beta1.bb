require ffmpeg.inc

LICENSE = "LGPLv2.1+ & GPLv2+"

SRC_URI = "https://github.com/xbmc/FFmpeg/archive/${PV}.tar.gz"
SRC_URI[md5sum] = "4810bf36952da59327a48482ec4bfedb"
SRC_URI[sha256sum] = "d7e7fdc96dafed1a1f273d4ace0a5b1ddcd75e9a5310bebd2ef0e36e65df41df"

S = "${WORKDIR}/FFmpeg-${PV}"

LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://libpostproc/postprocess.c;beginline=8;endline=21;md5=3163771dd725805aeda961a4e05173b5 \
                    file://COPYING.LGPLv2.1;md5=bd7a443320af8c812e4c18d1b79df004 \
                    file://ffmpeg.c;beginline=7;endline=20;md5=0385751f4c95cb9267cc125532118221"

EXTRA_FFCONF_armv7a = "--cpu=cortex-a8"
EXTRA_FFCONF_mipsel = "--arch=mips"

EXTRA_OECONF = " \
        --arch=${TARGET_ARCH} \
        --cross-prefix=${TARGET_PREFIX} \
        --disable-stripping \
        --enable-cross-compile \
        --enable-libtheora  \
        --enable-libvorbis \
        --enable-pthreads \
        --enable-shared \
        --enable-swscale \
        --enable-vaapi \
        --enable-gpl \
        --enable-yasm \
        --extra-cflags="${TARGET_CFLAGS} ${HOST_CC_ARCH}${TOOLCHAIN_OPTIONS}" \
        --extra-ldflags="${TARGET_LDFLAGS}" \
        --sysroot="${STAGING_DIR_TARGET}" \
        --prefix=${prefix}/ \
        --target-os=linux \
        ${EXTRA_FFCONF} \
"

do_configure() {
        ./configure ${EXTRA_OECONF}
}

FULL_OPTIMIZATION_armv7a = "-fexpensive-optimizations  -ftree-vectorize -fomit-frame-pointer -O4 -ffast-math"
BUILD_OPTIMIZATION = "${FULL_OPTIMIZATION}"
