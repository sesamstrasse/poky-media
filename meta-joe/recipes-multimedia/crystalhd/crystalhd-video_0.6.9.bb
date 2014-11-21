SECTION = "devel"
SUMMARY = "Crystal HD va-api module"
DESCRIPTION = "Crystal HD va-api module"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "libcrystalhd libva"

SRCREV = "97fbda38b4e0a1f4d63aab9f81a9c4cc238de70b"
SRC_URI = "git://gitorious.org/crystalhd-video/crystalhd-video.git \
           file://vaapi_032.patch \
           file://workaround_for_glx.patch"

S = "${WORKDIR}/git"

inherit autotools

FILES_${PN} += "${libdir}/dri"
FILES_${PN}-dbg += "${libdir}/dri/.debug"
