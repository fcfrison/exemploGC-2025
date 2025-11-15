#!/bin/bash

ARQ=`basename $1|sed "s/\.cmm//"`

java Parser $1 >./as/$ARQ.s
# 32 bits
# as -o ./obj/$ARQ.o ./as/$ARQ.s
#ld -o ./bin/$ARQ ./obj/$ARQ.o

# 64 bits 
as --32 -o ./obj/$ARQ.o ./as/$ARQ.s
ld -m elf_i386 -s -o ./bin/$ARQ ./obj/$ARQ.o
