SECTION = "devel"
SUMMARY = "Crystal HD kernel module"
DESCRIPTION = "Crystal HD kernel module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://crystalhd_hw.h;endline=25;md5=fc16e5852ff8db9d15e7192b43b7b05f"

DEPENDS = "virtual/kernel"

inherit module autotools

SRCREV = "fdd2f19ac739a3db1fd7469ea19ceaefe0822e5a"
SRC_URI = "git://git.linuxtv.org/jarod/crystalhd.git"

S = "${WORKDIR}/git/driver/linux"

EXTRA_OECONF="--with-kernel-path=${STAGING_KERNEL_DIR}"

do_configure_prepend() {
	sed -i 's/-Werror/-Wno-error=implicit-function-declaration/g' Makefile.in
}

do_install() {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${S}/20-crystalhd.rules ${D}${sysconfdir}/udev/rules.d

	install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/broadcom
	install -m 0644 ${S}/crystalhd.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/video/broadcom	
}
