package ru.job4j.cache.menu.action;

import ru.job4j.cache.DirFileCache;

public interface UserFileCacheAction extends UserAction {

    void setCache(DirFileCache cache);
}
