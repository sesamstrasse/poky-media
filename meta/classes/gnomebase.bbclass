def gnome_verdir(v):
    import re
    m = re.match("^([0-9]+)\.([0-9]+)", v)
    return "%s.%s" % (m.group(1), m.group(2))

GNOME_COMPRESS_TYPE ?= "bz2"
SECTION ?= "x11/gnome"
SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.${GNOME_COMPRESS_TYPE};name=archive"

DEPENDS += "gnome-common-native"

FILES_${PN} += "${datadir}/application-registry  \
	${datadir}/mime-info \
	${datadir}/mime/packages \	
	${datadir}/mime/application \
	${datadir}/gnome-2.0 \
	${datadir}/polkit* \
	${datadir}/GConf \
	${datadir}/glib-2.0/schemas \
"

FILES_${PN}-doc += "${datadir}/devhelp"

inherit autotools pkgconfig

do_install_append() {
	rm -rf ${D}${localstatedir}/lib/scrollkeeper/*
	rm -rf ${D}${localstatedir}/scrollkeeper/*
	rm -f ${D}${datadir}/applications/*.cache
}

