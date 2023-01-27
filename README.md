## Jag Unpacker

A small tool to unpack old runescape jar files. Builds are provided via the releases page and they include bundled JREs. 
To run this manually you need to run with **java versions up to 13** as pack200 support was removed in java 14.

### Usage

Download one of the release packages or compile yourself then run:

```
bin/jag-unpacker <path-to-main_file_cache.dat0>
```

A `runescape-unpacked.jar` file will be created in the same location as the dat0 file.