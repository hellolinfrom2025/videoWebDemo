package org.mintleaf;

import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

/**
 * * 一个单独的类体验Beetlsql，可以测试代码生成，内置的crud，还有sql查询
 * @Author: MengchuZhang
 * @Date: 2018/8/8 8:10
 * @Version 1.0
 */
public class BeetlSqlGen {
//	static String driver = "oracle.jdbc.OracleDriver";
//	static String url = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = 10.4.234.30)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 10.4.234.29)(PORT = 1521))(LOAD_BALANCE = yes))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = hwygk)(instance_name=hwygk1)(instance_name=hwygk2)(FAILOVER_MODE =(TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))";
//	static String userName="FZEQZHGL";
//	static String password="Hwfx2017";

//	static String driver = "oracle.jdbc.OracleDriver";
//	static String url = "jdbc:oracle:thin:@10.4.234.73:1521:yrcc";
//	static String userName="ZFRAME";
//	static String password="Zframe2016";

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://10.4.234.82:3306/mintleaf_fast?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
	static String userName="root";
	static String password="YRcc610";

	
	public static void main(String[] args) throws Exception {



		ConnectionSource source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
		//oracle数据库
//		DBStyle oracleStyle = new OracleStyle();
		//mysql数据库
		DBStyle mysql = new MySqlStyle();
		// sql语句放在classpagth的/sql 目录下
		SQLLoader loader = new ClasspathLoader("/sql");
		// 数据库命名跟java命名转化规则，UnderlinedNameConversion 指数据库表和列是下划线分割
		UnderlinedNameConversion nc = new  UnderlinedNameConversion();
		// 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
		SQLManager sqlManager = new SQLManager(mysql,loader,source,nc,new Interceptor[]{new DebugInterceptor()});
//		sqlManager.genSQLFile("HWSWJ.ST_SHIPIN_B",new GenConfig());
//		sqlManager.genPojoCode("HWSWJ.ST_SHIPIN_B","org.mintleaf.modules.test.entity");

		sqlManager.genSQLFile("core_button",new GenConfig());
		sqlManager.genPojoCode("core_button","org.mintleaf.modules.core");
		//生成实体类，以及sql的md文件
//		sqlManager.genALL("org.mintleaf.modules.core.entity", new GenConfig(), new GenFilter() {
//			@Override
//			public boolean accept(String tableName){
//				System.out.println(tableName);
////				if(tableName.equalsIgnoreCase("ST_SHIPIN_B")){
////					return true;
////				}else{
////					return false;
////				}
//				 return true;
//			}
//		});

	}

}
