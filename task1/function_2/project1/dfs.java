package project1;

import java.io.File;

public class dfs {
    public static String path;        //给定工作目录

    public dfs(String path) {     //这个directorycontents 是将来给commit的
        this.path = path;
    }

    //打开文件夹，并判别当前对象为文件/子文件夹
    public static String DFS(String path, String directoryContents) throws Exception {       //path其实就是指向test文件夹

        File dir = new File(path);                               //将path赋给File类的对象——dir
        File[] fs = dir.listFiles();                             //File类的数组用于存放将dir指向的文件夹打开后的各个File类的对象

        for (int i = 0; i < fs.length; i++) {                    //逐一访问
            if (fs[i].isFile()) {
                blob newblob = new blob(fs[i]);                  //在blob里传给行参f；

                newblob.content();                               //用于显示blob的内容，即Value值

                //存入HashMap中
                hash.hashADirectory.hashMap.put(newblob.getKey(), newblob.getContent());//左边是文件的键：哈希值；右边是文件的值：文件内容
                System.out.println(newblob.getKey());

                directoryContents += "blob " + "{" + newblob.getKey() + "} " + fs[i].getName() + "||";     //将当前文件的类型（blob）、名称、哈希值存放在directoryContents里面

            }

            //如果当前路径是文件夹
            if (fs[i].isDirectory()) {
                tree newtree = new tree(fs[i], path);
                System.out.println("文件夹" + newtree.name + "的哈希值是：" + newtree.key);

                //存入HashMap中
                hash.hashADirectory.hashMap.put(newtree.key, newtree.content);     //将文件夹的键值对放入HashMap内

                directoryContents += newtree.type + " " + "{" + newtree.getKey() + "} " + fs[i].getName() + "||";

            }
        }
        return directoryContents;
    }
}
