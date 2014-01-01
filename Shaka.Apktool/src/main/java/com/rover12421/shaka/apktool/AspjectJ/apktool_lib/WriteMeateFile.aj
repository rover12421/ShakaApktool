package com.rover12421.shaka.apktool.AspjectJ.apktool_lib;

import brut.androlib.AndrolibException;
import com.rover12421.shaka.apktool.util.ShakaRuntimeException;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * Created by rover12421 on 12/29/13.
 * 重写brut.androlib.Androlib中的
 * void writeMetaFile(File mOutDir, Map<String, Object> meta)
 * 方法,让 apktool.yml 以UTF-8格式保持.以便支持中文路径
 */
public aspect WriteMeateFile {
    //brut.androlib.Androlib;
    //void writeMetaFile(File mOutDir, Map<String, Object> meta)
    pointcut writeMeateFilePointcut(File mOutDir, Map<String, Object> meta)
            : execution(void brut.androlib.Androlib.writeMetaFile(File, Map<String, Object>))
            && args(mOutDir, meta);

    void around(File mOutDir, Map<String, Object> meta) : writeMeateFilePointcut(mOutDir, meta) && !within(WriteMeateFile +) {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        // options.setIndent(4);
        Yaml yaml = new Yaml(options);

        Writer writer = null;
        try {
//            writer = new FileWriter(new File(mOutDir, "apktool.yml"));
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File(mOutDir, "apktool.yml")), "UTF-8"));
            yaml.dump(meta, writer);
        } catch (IOException ex) {
            throw new ShakaRuntimeException(new AndrolibException(ex));
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                }
            }
        }
    }
}
