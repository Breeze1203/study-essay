利用maven,创建一个普通的web项目

加载依赖，在pom.xml文件中添加Spring依赖和日志相关依赖

  <dependencies>
      <!--测试相关-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
      <!--Spring核心基础依赖-->
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-core</artifactId>
          <version>5.0.2.RELEASE</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>5.0.2.RELEASE</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-beans</artifactId>
          <version>5.0.2.RELEASE</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-expression</artifactId>
          <version>5.0.2.RELEASE</version>
      </dependency>
      <!--日志相关-->
      <dependency>
          <groupId>commons-logging</groupId>
          <artifactId>commons-logging</artifactId>
          <version>1.2</version>
      </dependency>
      <dependency>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
          <version>1.2.17</version>
      </dependency>
      <dependency>
          <groupId>org.testng</groupId>
          <artifactId>testng</artifactId>
          <version>RELEASE</version>
          <scope>compile</scope>
      </dependency>

  </dependencies>

在resources资源目录点击右键，依次选择New-->XML Configuration File-->Spring Config，创建applicationContext.xml的配置文件