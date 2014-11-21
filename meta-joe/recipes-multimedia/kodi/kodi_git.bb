DESCRIPTION = "software media player and entertainment hub"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://copying.txt;md5=8c8473d035f42f5883d82c5f6828eba7"
DEPENDS = "libvorbis boost libass mysql5 mpeg2dec libmad libmodplug tiff yajl libtinyxml taglib libcdio jasper libmicrohttpd samba rtmpdump libbluray libnfs swig-native libva libfribidi libssh ffmpeg libxslt"

SRC_URI = "git://github.com/xbmc/xbmc.git"
SRCREV = "88a9a44264d0de569d8e114196ed544b84679adb"

PV = "14.0b3"

S = "${WORKDIR}/git"

inherit autotools gettext python-dir

# breaks compilation
CCACHE = ""

CACHED_CONFIGUREVARS += " \
    ac_cv_path_PYTHON="${STAGING_BINDIR_NATIVE}/python-native/python" \
"

EXTRA_OECONF = " \
    --disable-rpath \
    --disable-sdl \
    --disable-gl \
    --disable-vdpau \
    --disable-vaapi \
    --disable-openmax \
    --enable-gles \
    --enable-udev \
    --disable-debug \
    --disable-joystick \
    --disable-texturepacker \
    --with-ffmpeg=shared"

# for python modules
export HOST_SYS
export BUILD_SYS
export STAGING_LIBDIR
export STAGING_INCDIR
export PYTHON_DIR

do_configure() {
    sh bootstrap
    oe_runconf
}

FILES_${PN} += "${datadir} ${libdir}"
FILES_${PN}-dbg += "${libdir}/kodi/.debug ${libdir}/kodi/*/.debug ${libdir}/kodi/*/*/.debug ${libdir}/kodi/*/*/*/.debug"

RRECOMMENDS_${PN}_append = " libcec \
                             python \
                             python-lang \
                             python-re \
                             python-netclient \
                             libcurl \
                             xdpyinfo \
"

RRECOMMENDS_${PN}_append_libc-glibc = " glibc-charmap-ibm850 glibc-gconv-ibm850"

