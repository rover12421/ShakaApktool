package com.rover12421.shaka.apktool.directory;

import brut.directory.AbstractDirectory;
import brut.directory.DirectoryException;
import brut.directory.PathNotExist;
import brut.directory.ZipRODirectory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by rover12421 on 1/29/16.
 */
public class ZipRODirectoryEx extends ZipRODirectory {
    private org.apache.commons.compress.archivers.zip.ZipFile mZipFile;
    private String mPath;

    public ZipRODirectoryEx(String zipFileName) throws DirectoryException {
        this(zipFileName, "");
    }

    public ZipRODirectoryEx(File zipFile) throws DirectoryException {
        this(zipFile, "");
    }

    public ZipRODirectoryEx(ZipFile zipFile) throws DirectoryException {
        this(zipFile, "");
    }

    public ZipRODirectoryEx(String zipFileName, String path) throws DirectoryException {
        this(new File(zipFileName), path);
    }

    public ZipRODirectoryEx(File zipFile, String path) throws DirectoryException {
        super();
        try {
            mZipFile = new org.apache.commons.compress.archivers.zip.ZipFile(zipFile);
        } catch (IOException e) {
            throw new DirectoryException(e);
        }
        mPath = path;
    }

    public ZipRODirectoryEx(ZipFile zipFile, String path) throws DirectoryException {
        this(zipFile.getName(), path);
    }

    public ZipRODirectoryEx(org.apache.commons.compress.archivers.zip.ZipFile zipFile, String path) {
        mZipFile = zipFile;
        mPath = path;
    }

//    @Override
//    protected AbstractDirectory createDirLocal(String name) throws DirectoryException {
//        return super.createDirLocal(name);
//    }

    @Override
    protected InputStream getFileInputLocal(String name) throws DirectoryException {
        try {
            System.out.println("getFileInputLocal : " + mPath + name);
            return mZipFile.getInputStream(mZipFile.getEntry(mPath + name));
        } catch (IOException e) {
            throw new PathNotExist(name, e);
        }
    }

//    @Override
//    protected OutputStream getFileOutputLocal(String name) throws DirectoryException {
//        return super.getFileOutputLocal(name);
//    }

    @Override
    protected void loadDirs() {
        loadAll();
    }

    @Override
    protected void loadFiles() {
        loadAll();
    }

//    @Override
//    protected void removeFileLocal(String name) {
//        super.removeFileLocal(name);
//    }

    @Override
    public int getCompressionLevel(String fileName) throws DirectoryException {
        System.out.println("getCompressionLevel : " + fileName);
        ZipArchiveEntry entry = mZipFile.getEntry(fileName);
        return entry.getMethod();
    }

    private void loadAll() {
        mFiles = new LinkedHashSet();
        mDirs = new LinkedHashMap();

        int prefixLen = getPath().length();
        Enumeration<ZipArchiveEntry> entries = getZipFile().getEntries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            String name = entry.getName();

            if (name.equals(getPath()) || ! name.startsWith(getPath())) {
                continue;
            }

            String subname = name.substring(prefixLen);

            int pos = subname.indexOf(separator);
            if (pos == -1) {
                if (! entry.isDirectory()) {
                    mFiles.add(subname);
                    continue;
                }
            } else {
                subname = subname.substring(0, pos);
            }

            if (! mDirs.containsKey(subname)) {
                AbstractDirectory dir = new ZipRODirectoryEx(getZipFile(), getPath() + subname + separator);
                mDirs.put(subname, dir);
            }
        }
    }

    private String getPath() {
        return mPath;
    }

    private org.apache.commons.compress.archivers.zip.ZipFile getZipFile() {
        return mZipFile;
    }
}
