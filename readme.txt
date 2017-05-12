2017.5.11 
关于ViewPager应用。 
1、用List将所有的Item放入viewPager的LIst中，然后添加，类似Listview的Item添加 
2、用Adapter加载，类似Listview
 3、如果要有tab需要跟随页面变化，那么就要设置tab的按键监听，用setcurrentItem设置页面的切换
 4、tab的变化要用setpageonchangelistener来设置变化。但现在这个方法已经被抛弃，待考证资料