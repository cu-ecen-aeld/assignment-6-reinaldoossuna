LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8ed1a118f474eea5e159b560c339329b"

inherit module

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-reinaldoossuna.git;protocol=ssh;branch=main \
           file://S98aesdmodules \
           file://0001-modify-Makefile-to-accept-KERNEL_SRC.patch \
           file://0002-add-LICENSE-file-to-folder.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "2b11676c24aa1c218f4e928011433da62a94bd82"

S = "${WORKDIR}/git/aesd-char-driver"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}"

RPROVIDES:${PN} += "kernel-module-aesdchar"

FILES:${PN} += "${sysconfdir}/init.d/S98aesdmodules"
INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "S98aesdmodules"
INITSCRIPT_PARAMS = "defaults 99"
inherit update-rc.d


do_install:append () {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/S98aesdmodules ${D}${sysconfdir}/init.d
}
