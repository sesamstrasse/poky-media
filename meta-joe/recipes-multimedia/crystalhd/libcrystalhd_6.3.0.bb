SECTION = "devel"
SUMMARY = "Crystal HD kernel module"
DESCRIPTION = "Crystal HD kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://libcrystalhd_if.h;endline=27;md5=47e11e186854393058767fc506078188"

RDEPENDS = "crystalhd-module"

SRCREV = "fdd2f19ac739a3db1fd7469ea19ceaefe0822e5a"
SRC_URI = "git://git.linuxtv.org/jarod/crystalhd.git"

S = "${WORKDIR}/git/linux_lib/libcrystalhd"

EXTRA_OEMAKE = "'BCGCC=${CXX}' \
                'LDFLAGS=-Wl,-soname,libcrystalhd.so.6 -pthread'"

do_configure() {
	sed -i 's!-I/usr/include!!g' Makefile
}

do_install() {
	oe_runmake install DESTDIR=${D}
}

FILES_${PN} += "/lib/firmware"
