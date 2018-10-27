package org.singular

enum OutputArtifact {

    AST,
    AST_JSON,
    AST_COMPACT_JSON,
    ASM,
    ASM_JSON,
    OPCODES,
    BIN,
    BIN_RUNTIME,
    CLONE_BIN,
    ABI,
    HASHES,
    USERDOC,
    DEVDOC,
    METADATA

    @Override
    String toString() {
        return name().replaceAll('_', '-').toLowerCase()
    }

}