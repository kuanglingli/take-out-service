package com.itaem.crazy.shirodemo.utils;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneratorMybatisPlus {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 选择 freemarker 引擎，默认 Veloctiy
        // mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //生成文件的输出目录
        String path= "C:\\soft\\workspace\\take-out-service\\src\\main\\java";
        globalConfig.setOutputDir(path);
        // Author设置作者
        globalConfig.setAuthor("Li Chaozhu");
        // 文件覆盖
        globalConfig.setFileOverride(true);
        // 生成后打开文件
        globalConfig.setOpen(false);
        // 自定义文件名风格，%s自动填充表实体属性
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setEntityName("%sDO");
        globalConfig.setControllerName("%sController");
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert());
//        dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){
//            // 自定义数据库表字段类型转换【可选】
//            @Override
//            public DbColumnType processTypeConvert(String fieldType) {
//                System.out.println("转换类型：" + fieldType);
//                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
//                return super.processTypeConvert(fieldType);
//            }
//        });
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/shirodemo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        autoGenerator.setDataSource(dataSourceConfig);

        // 包名配置
        PackageConfig packageConfig = new PackageConfig();
        // 父包和子包名分开处理
        packageConfig.setParent("com.itaem.crazy.shirodemo.project");
        packageConfig.setController("controller");
        packageConfig.setEntity("DO");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        autoGenerator.setPackageInfo(packageConfig);

        // 生成策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
        //设置命名格式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 实体是否为lombok模型，默认 false
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //表和前缀处理
//        strategy.setInclude(new String[] { "cust","shop"});// 需要生成的表
//        strategy.setInclude("dt_defined_log".split(","));
        String[] tablePre = new String[]{"t_"};
        strategy.setTablePrefix(tablePre);
        autoGenerator.setStrategy(strategy);
        // 执行，以上相关参数可以基于动态输入获取
        autoGenerator.execute();
    }
}
