TravelFootprint v1.0 个人旅行足迹管理系统

-----项目简介
这是一个基于JavaSE开发的控制台项目，用于记录用户的旅行信息，该项目用JDBC连接MySQL，旨在理解底层数据库交互的核心
可以实现旅行、景点、美食、花费的增删查改，以及去过的城市的查询

我用了一个月左右的时间完成了这个项目，从数据库表的设计、建表、SQL练习、数据添加，
到用JDBC连接数据库，了解一些JDBC的API，例如DriverManager、Connection、PrepareedStatement、ResultSet等
再到数据库到Java的映射，实体类的编写，随后用Dao完成用Java语言操控数据库
然后用Service操控组合Dao，来实现业务逻辑，涉及多张表的需要在Service中开启事务
然后设计UI界面，分成几个Menu防止Main太过繁杂，把副的Menu用MainMenu来组合，最后在Main中调用


-----技术栈
-开发语言：Java (JDK17)
-数据库：MySQL
-数据访问：JDBC
-构建工具：Maven
-版本控制：Git

-----系统架构
项目采用了三层架构，UI层、业务逻辑层、数据访问层完全解耦
-Entity层：这是数据库在Java中的映射，一张表对应一个完整的JavaBean类
-Dao层（Data Access Object）：负责执行SQL语句，一个SQL语句对应一个方法，涉及事务的方法需要在形参中传入Connection
-Service层：负责核心业务逻辑，组装Dao，开启事务都在这里
-UI层（Menu）：负责在控制台向用户展示，获取用户输入，调用Service中的方法来处理，不能在Menu中调用Dao

-------------数据库----------------

-----数据库设计
一共九张表，tb_travel tb_province tb_city tb_scenic tb_food tb_expense
         tb_travel_city tb_travel_scenic tb_expense_category
-字典表：tb_province、tb_city，里面记录了中国所有省级行政区以及地级行政区，跟城市、省份相关的数据从里面取，不能增删改
        tb_expense_category，里面记录了花费类型
-跟事务强相关的：tb_expense、tb_travel_city、tb_travel_scenic，删除旅行，必须删除这次旅行的花费和这次旅行去的城市和景点，不要在城市或者景点表里面删

---数据库关系
-一对多：tb_province - tb_city；tb_expense_category - tb_expense；tb_travel - tb_expense
-多对多：tb_travel - tb_city；tb_travel - tb_scenic；tb_travel - tb_food
-----数据库问题
其实我感觉这块数据库设计的有些问题，既然tb_scenic tb_food都不是字典表，都只是旅行去过的景点和吃过的食物，
那只要tb_travel_scenic和tb_travel_food不就行了吗，没必要建Scenic表，里面只记录了景点的基础数据
而有较大问题的就是food表，里面记录了评分，我认为不应该这样，每次旅行，我都可能对同一种食物有不同评分，我只能说我这一次对这个食物评分多少
所以我认为，应该建tb_travel_food表，现在的food只有cityid，只知道是哪个城市，不知道是哪次旅行
所以food表的设计逻辑问题比较大，由于我1.0的首要目的是练习独自打代码，所以逻辑上的事情没太注意
在我看来，美食的数据量远高于景点和城市。







