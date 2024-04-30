# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-reinaldoossuna.git;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "e6cee71c81560aaed0dff24b36634313244b2b18"

S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket"
TARGET_LDFLAGS += "-pthread -lrt"
RDEPENDS:${PN} = "libgcc"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aesdsocket-start-stop"
INITSCRIPT_PARAMS = "defaults 99"
inherit update-rc.d

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d
}
