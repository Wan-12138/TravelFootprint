-- =====================================================
-- TravelFootprint 数据库初始化脚本
-- =====================================================
-- 使用方法（任选其一）：
--   1. 在 MySQL 客户端（如 Navicat / MySQL Workbench / 命令行）中直接执行本文件；
--   2. 命令行导入：mysql -u root -p < sql/travel.sql
--
-- 说明：本脚本会创建 travel 库、9 张表，并插入省份/城市等初始数据。

CREATE DATABASE IF NOT EXISTS `travel` DEFAULT CHARACTER SET utf8mb4;
USE `travel`;

-- -----------------------------------------------------
-- 1. 省份表（字典表，不增删改）
-- -----------------------------------------------------
CREATE TABLE tb_province (
    province_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '省份编号',
    province VARCHAR(20) NOT NULL UNIQUE COMMENT '省份名称'
) COMMENT '省份表';

INSERT INTO tb_province (province)
VALUES ('北京'),('天津'),('河北'),
       ('辽宁'),('吉林'),('黑龙江'),('内蒙古'),
       ('山东'),('山西'),('河南'),('安徽'),
       ('上海'),('江苏'),('浙江'),('江西'),
       ('福建'),('广东'),('广西'),('海南'),
       ('湖南'),('湖北'),('陕西'),('宁夏'),
       ('贵州'),('云南'),('四川'),('重庆'),
       ('甘肃'),('青海'),('西藏'),('新疆'),
       ('香港'),('澳门'),('台湾');

-- -----------------------------------------------------
-- 2. 城市表（字典表，不增删改）
-- -----------------------------------------------------
CREATE TABLE tb_city (
    city_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '城市id',
    city VARCHAR(20) NOT NULL UNIQUE COMMENT '城市名',
    province_id INT COMMENT '所属省份id',
    FOREIGN KEY (province_id) REFERENCES tb_province(province_id)
) COMMENT '城市表';

-- 先插入 5 个示例城市（city_id = 1~5，后续景点/美食/旅行示例数据会引用这些 id）
INSERT INTO tb_city(city, province_id) VALUES
('大连',4),
('济南',8),
('泰安',8),
('嘉兴',14),
('哈尔滨',6);

-- 再插入全国城市（顺序与 province_id 对应）
INSERT INTO tb_city (city, province_id) VALUES
-- 1. 北京
('北京', 1),
-- 2. 天津
('天津', 2),
-- 3. 河北
('石家庄', 3), ('唐山', 3), ('秦皇岛', 3), ('邯郸', 3), ('邢台', 3), ('保定', 3), ('张家口', 3), ('承德', 3), ('沧州', 3), ('廊坊', 3), ('衡水', 3),
-- 4. 辽宁
('沈阳', 4), ('鞍山', 4), ('抚顺', 4), ('本溪', 4), ('丹东', 4), ('锦州', 4), ('营口', 4), ('阜新', 4), ('辽阳', 4), ('盘锦', 4), ('铁岭', 4), ('朝阳', 4), ('葫芦岛', 4),
-- 5. 吉林
('长春', 5), ('吉林', 5), ('四平', 5), ('辽源', 5), ('通化', 5), ('白山', 5), ('松原', 5), ('白城', 5), ('延边', 5),
-- 6. 黑龙江
('齐齐哈尔', 6), ('鸡西', 6), ('鹤岗', 6), ('双鸭山', 6), ('大庆', 6), ('伊春', 6), ('佳木斯', 6), ('七台河', 6), ('牡丹江', 6), ('黑河', 6), ('绥化', 6), ('大兴安岭', 6),
-- 7. 内蒙古
('呼和浩特', 7), ('包头', 7), ('乌海', 7), ('赤峰', 7), ('通辽', 7), ('鄂尔多斯', 7), ('呼伦贝尔', 7), ('巴彦淖尔', 7), ('乌兰察布', 7), ('兴安盟', 7), ('锡林郭勒', 7), ('阿拉善', 7),
-- 8. 山东
('青岛', 8), ('淄博', 8), ('枣庄', 8), ('东营', 8), ('烟台', 8), ('潍坊', 8), ('济宁', 8), ('威海', 8), ('日照', 8), ('临沂', 8), ('德州', 8), ('聊城', 8), ('滨州', 8), ('菏泽', 8),
-- 9. 山西
('太原', 9), ('大同', 9), ('阳泉', 9), ('长治', 9), ('晋城', 9), ('朔州', 9), ('晋中', 9), ('运城', 9), ('忻州', 9), ('临汾', 9), ('吕梁', 9),
-- 10. 河南
('郑州', 10), ('开封', 10), ('洛阳', 10), ('平顶山', 10), ('安阳', 10), ('鹤壁', 10), ('新乡', 10), ('焦作', 10), ('濮阳', 10), ('许昌', 10), ('漯河', 10), ('三门峡', 10), ('南阳', 10), ('商丘', 10), ('信阳', 10), ('周口', 10), ('驻马店', 10), ('济源', 10),
-- 11. 安徽
('合肥', 11), ('芜湖', 11), ('蚌埠', 11), ('淮南', 11), ('马鞍山', 11), ('淮北', 11), ('铜陵', 11), ('安庆', 11), ('黄山', 11), ('滁州', 11), ('阜阳', 11), ('宿州', 11), ('六安', 11), ('亳州', 11), ('池州', 11), ('宣城', 11),
-- 12. 上海
('上海', 12),
-- 13. 江苏
('南京', 13), ('无锡', 13), ('徐州', 13), ('常州', 13), ('苏州', 13), ('南通', 13), ('连云港', 13), ('淮安', 13), ('盐城', 13), ('扬州', 13), ('镇江', 13), ('泰州', 13), ('宿迁', 13),
-- 14. 浙江
('杭州', 14), ('宁波', 14), ('温州', 14), ('湖州', 14), ('绍兴', 14), ('金华', 14), ('衢州', 14), ('舟山', 14), ('台州', 14), ('丽水', 14),
-- 15. 江西
('南昌', 15), ('景德镇', 15), ('萍乡', 15), ('九江', 15), ('新余', 15), ('鹰潭', 15), ('赣州', 15), ('吉安', 15), ('宜春', 15), ('抚州', 15), ('上饶', 15),
-- 16. 福建
('福州', 16), ('厦门', 16), ('莆田', 16), ('三明', 16), ('泉州', 16), ('漳州', 16), ('南平', 16), ('龙岩', 16), ('宁德', 16),
-- 17. 广东
('广州', 17), ('韶关', 17), ('深圳', 17), ('珠海', 17), ('汕头', 17), ('佛山', 17), ('江门', 17), ('湛江', 17), ('茂名', 17), ('肇庆', 17), ('惠州', 17), ('梅州', 17), ('汕尾', 17), ('河源', 17), ('阳江', 17), ('清远', 17), ('东莞', 17), ('中山', 17), ('潮州', 17), ('揭阳', 17), ('云浮', 17),
-- 18. 广西
('南宁', 18), ('柳州', 18), ('桂林', 18), ('梧州', 18), ('北海', 18), ('防城港', 18), ('钦州', 18), ('贵港', 18), ('玉林', 18), ('百色', 18), ('贺州', 18), ('河池', 18), ('来宾', 18), ('崇左', 18),
-- 19. 海南
('海口', 19), ('三亚', 19), ('三沙', 19), ('儋州', 19), ('琼海', 19), ('文昌', 19), ('万宁', 19), ('五指山', 19),
-- 20. 湖南
('长沙', 20), ('株洲', 20), ('湘潭', 20), ('衡阳', 20), ('邵阳', 20), ('岳阳', 20), ('常德', 20), ('张家界', 20), ('益阳', 20), ('郴州', 20), ('永州', 20), ('怀化', 20), ('娄底', 20), ('湘西', 20),
-- 21. 湖北
('武汉', 21), ('黄石', 21), ('十堰', 21), ('宜昌', 21), ('襄阳', 21), ('鄂州', 21), ('荆门', 21), ('孝感', 21), ('荆州', 21), ('黄冈', 21), ('咸宁', 21), ('随州', 21), ('恩施', 21),
-- 22. 陕西
('西安', 22), ('铜川', 22), ('宝鸡', 22), ('咸阳', 22), ('渭南', 22), ('延安', 22), ('汉中', 22), ('榆林', 22), ('安康', 22), ('商洛', 22),
-- 23. 宁夏
('银川', 23), ('石嘴山', 23), ('吴忠', 23), ('固原', 23), ('中卫', 23),
-- 24. 贵州
('贵阳', 24), ('六盘水', 24), ('遵义', 24), ('安顺', 24), ('毕节', 24), ('铜仁', 24), ('黔西南', 24), ('黔东南', 24), ('黔南', 24),
-- 25. 云南
('昆明', 25), ('曲靖', 25), ('玉溪', 25), ('保山', 25), ('昭通', 25), ('丽江', 25), ('普洱', 25), ('临沧', 25), ('楚雄', 25), ('红河', 25), ('文山', 25), ('西双版纳', 25), ('大理', 25), ('德宏', 25), ('怒江', 25), ('迪庆', 25),
-- 26. 四川
('成都', 26), ('自贡', 26), ('攀枝花', 26), ('泸州', 26), ('德阳', 26), ('绵阳', 26), ('广元', 26), ('遂宁', 26), ('内江', 26), ('乐山', 26), ('南充', 26), ('眉山', 26), ('宜宾', 26), ('广安', 26), ('达州', 26), ('雅安', 26), ('巴中', 26), ('资阳', 26), ('阿坝', 26), ('甘孜', 26), ('凉山', 26),
-- 27. 重庆
('重庆', 27),
-- 28. 甘肃
('兰州', 28), ('嘉峪关', 28), ('金昌', 28), ('白银', 28), ('天水', 28), ('武威', 28), ('张掖', 28), ('平凉', 28), ('酒泉', 28), ('庆阳', 28), ('定西', 28), ('陇南', 28), ('临夏', 28), ('甘南', 28),
-- 29. 青海
('西宁', 29), ('海东', 29), ('海北', 29), ('黄南', 29), ('海南州', 29), ('果洛', 29), ('玉树', 29), ('海西', 29),
-- 30. 西藏
('拉萨', 30), ('日喀则', 30), ('昌都', 30), ('林芝', 30), ('山南', 30), ('那曲', 30), ('阿里', 30),
-- 31. 新疆
('乌鲁木齐', 31), ('克拉玛依', 31), ('吐鲁番', 31), ('哈密', 31), ('昌吉', 31), ('博尔塔拉', 31), ('巴音郭楞', 31), ('阿克苏', 31), ('克孜勒苏', 31), ('喀什', 31), ('和田', 31), ('伊犁', 31), ('塔城', 31), ('阿勒泰', 31),
-- 32. 香港
('香港', 32),
-- 33. 澳门
('澳门', 33),
-- 34. 台湾
('台北', 34), ('高雄', 34), ('台中', 34), ('台南', 34), ('新竹', 34), ('基隆', 34), ('嘉义', 34);

-- -----------------------------------------------------
-- 3. 景点表
-- -----------------------------------------------------
CREATE TABLE tb_scenic (
    scenic_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '景区id',
    scenic VARCHAR(20) NOT NULL COMMENT '景点名称',
    type VARCHAR(10) NOT NULL COMMENT '景区类型',
    scenic_city_id INT NOT NULL COMMENT '所属城市id',
    FOREIGN KEY (scenic_city_id) REFERENCES tb_city(city_id),
    CONSTRAINT unique_city_scenic UNIQUE (scenic_city_id, scenic)
) COMMENT '景点表';

INSERT INTO tb_scenic (scenic, type, scenic_city_id)
VALUES ('傅家庄海水浴场','海边',1),
       ('老虎滩海洋公园','游乐园',1),
       ('威尼斯水城','商业街',1),
       ('金石滩','海岸地质公园',1),
       ('趵突泉','泉水',2),
       ('大明湖','湖泊',2),
       ('泰山','山岳景区',3),
       ('乌镇','古镇',4),
       ('冰雪大世界','游乐园',5);

-- -----------------------------------------------------
-- 4. 美食表
-- -----------------------------------------------------
CREATE TABLE tb_food (
    food_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '食物id',
    food VARCHAR(20) NOT NULL COMMENT '食物名称',
    flavor VARCHAR(20) COMMENT '口味',
    taste_score INT CHECK (taste_score >= 0 AND taste_score <= 5) COMMENT '味道评分',
    valueformoney_score INT CHECK (valueformoney_score >= 0 AND valueformoney_score <= 5) COMMENT '性价比评分',
    city_id INT COMMENT '所属城市id',
    FOREIGN KEY (city_id) REFERENCES tb_city(city_id)
) COMMENT '美食表';

INSERT INTO tb_food(food, flavor, taste_score, valueformoney_score, city_id)
VALUES ('黄河大鲤鱼','咸、腥',3,2,2),
       ('把子肉','咸',4,5,2),
       ('炒鸡','咸、辣',4,3,2),
       ('东坡肉','咸',4,4,4);

-- -----------------------------------------------------
-- 5. 旅行表
-- -----------------------------------------------------
CREATE TABLE tb_travel (
    travel_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '旅行id',
    begin_date DATE NOT NULL COMMENT '开始日期',
    end_date DATE NOT NULL COMMENT '结束日期',
    convenience_score INT CHECK (convenience_score >= 0 AND convenience_score <= 5) COMMENT '旅行便利度',
    food_score INT CHECK (food_score >= 0 AND food_score <= 5) COMMENT '饮食',
    experience_score INT CHECK (experience_score >= 0 AND experience_score <= 5) COMMENT '旅行体验',
    CONSTRAINT check_dates CHECK (begin_date <= end_date)
) COMMENT '旅行表';

INSERT INTO tb_travel(begin_date, end_date, convenience_score, food_score, experience_score)
VALUES ('2024-06-16','2024-06-19',4,3,3),
       ('2024-08-30','2024-09-05',4,3,4),
       ('2025-02-05','2025-02-09',4,4,4);

-- -----------------------------------------------------
-- 6. 旅行-城市表
-- -----------------------------------------------------
CREATE TABLE tb_travel_city (
    tc_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '旅行——城市id',
    tc_travel_id INT NOT NULL COMMENT '旅行id',
    tc_city_id INT NOT NULL COMMENT '城市id',
    tc_begin_date DATE NOT NULL COMMENT '该城市旅行开始时间',
    tc_end_date DATE NOT NULL COMMENT '该城市旅行结束时间',
    tc_convenience_score INT CHECK (tc_convenience_score >= 0 AND tc_convenience_score <= 5) COMMENT '城市旅行便利度',
    tc_climate_score INT CHECK (tc_climate_score >= 0 AND tc_climate_score <= 5) COMMENT '城市气候',
    tc_food_score INT CHECK (tc_food_score >= 0 AND tc_food_score <= 5) COMMENT '城市饮食',
    tc_experience_score INT CHECK (tc_experience_score >= 0 AND tc_experience_score <= 5) COMMENT '城市旅行体验',
    tc_valueformoney_score INT CHECK (tc_valueformoney_score >= 0 AND tc_valueformoney_score <= 5) COMMENT '城市消费性价比',
    FOREIGN KEY (tc_travel_id) REFERENCES tb_travel(travel_id),
    FOREIGN KEY (tc_city_id) REFERENCES tb_city(city_id),
    CONSTRAINT unique_travel_city UNIQUE (tc_travel_id, tc_city_id)
) COMMENT '旅行——城市表';

INSERT INTO tb_travel_city(tc_travel_id, tc_city_id, tc_begin_date, tc_end_date, tc_convenience_score, tc_climate_score, tc_food_score, tc_experience_score, tc_valueformoney_score)
VALUES (1,1,'2024-06-16','2024-06-19',4,3,3,3,2),
       (2,2,'2024-08-30','2024-09-01',4,2,3,3,4),
       (2,3,'2024-09-02','2024-09-03',4,3,3,4,4),
       (2,4,'2024-09-04','2024-09-05',3,3,3,3,2),
       (3,5,'2025-02-05','2025-02-09',4,2,4,4,4);

-- -----------------------------------------------------
-- 7. 旅行-景区表
-- -----------------------------------------------------
CREATE TABLE tb_travel_scenic (
    ts_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '旅行景点id',
    travel_id INT NOT NULL COMMENT '旅行表外键',
    scenic_id INT NOT NULL COMMENT '景区表外键',
    scenic_visit_date DATE NOT NULL COMMENT '景点游览日期',
    scenic_convenience_score INT CHECK (scenic_convenience_score >= 0 AND scenic_convenience_score <= 5) COMMENT '便利度评分',
    scenic_valueformoney_score INT CHECK (scenic_valueformoney_score >= 0 AND scenic_valueformoney_score <= 5) COMMENT '门票性价比评分',
    scenic_comfort_score INT CHECK (scenic_comfort_score >= 0 AND scenic_comfort_score <= 5) COMMENT '景区舒适度评分',
    scenic_experience_score INT CHECK (scenic_experience_score >= 0 AND scenic_experience_score <= 5) COMMENT '游玩体验评分',
    FOREIGN KEY (travel_id) REFERENCES tb_travel(travel_id),
    FOREIGN KEY (scenic_id) REFERENCES tb_scenic(scenic_id),
    CONSTRAINT unique_travel_scenic UNIQUE (travel_id, scenic_id, scenic_visit_date)
) COMMENT '旅行——景区表';

INSERT INTO tb_travel_scenic(travel_id, scenic_id, scenic_visit_date, scenic_convenience_score, scenic_valueformoney_score, scenic_comfort_score, scenic_experience_score)
VALUES (1,1,'2024-6-16',3,3,3,4),
       (1,2,'2024-6-17',4,3,2,3),
       (1,3,'2024-6-18',3,2,3,2),
       (1,4,'2024-6-19',2,2,2,3),
       (2,5,'2024-8-31',3,4,2,3),
       (2,6,'2024-9-1',3,3,3,3),
       (2,7,'2024-9-2',3,3,3,4),
       (2,8,'2024-9-4',2,2,2,3),
       (3,9,'2025-2-7',4,2,3,4);

-- -----------------------------------------------------
-- 8. 花费类别表
-- -----------------------------------------------------
CREATE TABLE tb_expense_category (
    category_id INT PRIMARY KEY AUTO_INCREMENT COMMENT '花费类别id',
    category VARCHAR(20) NOT NULL UNIQUE COMMENT '花费类别'
) COMMENT '花费类别表';

INSERT INTO tb_expense_category(category) VALUES ('大交通'),('小交通'),('住宿'),('餐饮'),('门票'),('其他');

-- -----------------------------------------------------
-- 9. 旅行-花费表
-- -----------------------------------------------------
CREATE TABLE tb_expense (
    expense_id INT PRIMARY KEY AUTO_INCREMENT,
    expense_travel_id INT NOT NULL COMMENT '记录是哪次旅行的花费',
    expense_city_id INT COMMENT '记录是哪座城市的花费',
    expense_category_id INT NOT NULL COMMENT '记录是哪种类型的花费',
    expense_amount DECIMAL(10,2) NOT NULL CHECK (expense_amount > 0) COMMENT '花费金额',
    expense_date DATE NOT NULL COMMENT '花费日期',
    expense_description VARCHAR(50) NOT NULL COMMENT '花费描述',
    FOREIGN KEY (expense_travel_id) REFERENCES tb_travel(travel_id),
    FOREIGN KEY (expense_city_id) REFERENCES tb_city(city_id),
    FOREIGN KEY (expense_category_id) REFERENCES tb_expense_category(category_id)
) COMMENT '旅行——花费表';

INSERT INTO tb_expense(expense_travel_id, expense_city_id, expense_amount, expense_date, expense_category_id, expense_description)
VALUES (1,1,166,'2024-6-16',1,'沈阳——大连'),
       (1,1,166.5,'2024-6-19',1,'大连——沈阳'),
       (2,2,138.5,'2024-8-29',1,'沈阳——大明湖'),
       (2,3,15.5,'2024-9-1',1,'济南——泰山'),
       (2,4,150,'2024-9-4',1,'泰山——杭州——乌镇');
