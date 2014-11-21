DESCRIPTION = "Image with KODI."

IMAGE_FEATURES += "package-management x11-base ssh-server-dropbear"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL_append = " \
    mdadm \
    libcrystalhd \
    crystalhd-video \
    kodi \
"

