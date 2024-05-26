# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

inherit module

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-reinaldoossuna.git;protocol=ssh;branch=main \
           file://0001-install-only-misc-modules-and-scull.patch \
           file://0001-modify-makefile-to-receive-KERNEL_SRC.patch \
           file://S98lddmodules \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "718cbdf07e082486e008537660e6b9fb4fb07a45"

S = "${WORKDIR}/git"

EXTRA_OEMAKE:append:task-install = " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"

RPROVIDES:${PN} += "kernel-module-misc-modules"
