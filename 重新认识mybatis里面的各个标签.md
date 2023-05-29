重新认识mybatis里面的各个标签



在 MyBatis 中，`<result>` 标签用于定义如何将 SQL 查询结果映射到 Java 对象的属性或字段。它通常用于在 `resultMap` 元素中定义属性或字段的映射关系。

`<result>` 标签有以下属性：

- `property`：指定 Java 对象的属性或字段名，用于映射查询结果中的列。
- `column`：指定查询结果中的列名，用于映射到 Java 对象的属性或字段。
- `jdbcType`：指定查询结果中的列的 JDBC 类型。
- `javaType`：指定 Java 对象属性或字段的类型。
- `typeHandler`：指定用于处理查询结果中的列值的类型处理器。
- `select`：指定另一个 SQL 映射语句的 ID，用于从多个表或查询中映射数据。

`<result>` 标签的作用是将查询结果映射到 Java 对象的属性或字段中，使得我们可以在 Java 代码中方便地访问查询结果。

在 MyBatis 中，`resultMap` 元素用于定义如何将 SQL 查询结果映射到 Java 对象。`resultMap` 元素可以包含以下子元素：

1. `id`：`resultMap` 的唯一标识符。

2. result：用于定义如何将 SQL 查询结果映射到 Java 对象的属性或字段。

   result元素可以包含以下属性：

   - `property`：Java 对象的属性或字段名。
   - `column`：SQL 查询结果集中的列名。
   - `javaType`：Java 对象属性或字段的类型。
   - `jdbcType`：SQL 查询结果集中的列的 JDBC 类型。
   - `typeHandler`：用于处理 Java 对象属性或字段与 SQL 查询结果集中的列之间的转换的类型处理器。
   - `select`：用于指定要执行的 SQL 语句的 ID，以便在映射 Java 对象时使用。该属性通常用于在多个表之间进行映射或在映射复杂对象关系时使用。

3. `association`：用于定义一个 Java 对象属性与其他 Java 对象之间的关联关系。

4. `collection`：用于定义一个 Java 对象属性与多个 Java 对象之间的关联关系。

5. `discriminator`：用于根据某些条件选择不同的映射方案。

6. `constructor`：用于定义一个 Java 对象的构造函数，以便在映射 SQL 查询结果时使用。

这些子元素可以组合使用，以便定义更复杂的映射关系

MyBatis中的`<sql>`标签用于定义可重用的SQL片段。通过使用`<sql>`标签，可以在多个SQL语句中重复使用相同的SQL片段，从而避免了代码重复。 

`<sql>`标签可以包含任何有效的SQL语句，包括SELECT、INSERT、UPDATE、DELETE等语句。使用`<sql>`标签，可以定义包含参数的SQL片段，这些参数可以在其他SQL语句中使用。 

例如，如果在多个SQL语句中需要使用相同的WHERE条件，可以使用`<sql>`标签定义该条件，然后在其他SQL语句中引用它。这样可以避免在多个SQL语句中重复编写相同的WHERE条件，从而提高代码的可维护性和可读性。

以下是一个使用`<sql>`标签的示例：

```
<sql id="where_condition">
    WHERE status = #{status}
</sql>

<select id="getUserList" resultType="User">
    SELECT * FROM users
    <include refid="where_condition"/>
</select>

<select id="getAdminList" resultType="Admin">
    SELECT * FROM admins
    <include refid="where_condition"/>
</select>
```

在上面的示例中，`<sql>`标签定义了一个名为`where_condition`的SQL片段，该片段包含一个WHERE条件。在`getUserList`和`getAdminList`两个SQL语句中，使用`<include>`标签引用了`where_condition`片段，从而避免了在多个SQL语句中重复编写相同的WHERE条件。

MyBatis是一款流行的Java持久层框架，它提供了丰富的标签和功能，用于定义和执行SQL语句，以及将查询结果映射为Java对象。下面是MyBatis中一些常用的标签及其作用、应用场景和测试案例：

1. `<select>`标签：用于执行SELECT语句并将结果映射为Java对象。该标签可以指定SQL语句、参数类型、返回类型等属性。应用场景包括查询单个对象、查询列表、分页查询等。以下是一个查询单个对象的示例：

```
<select id="getUserById" parameterType="int" resultType="User">
    SELECT * FROM users WHERE id = #{id}
</select>
```

2. `<insert>`标签：用于执行INSERT语句并返回受影响的行数或自动生成的主键。该标签可以指定SQL语句、参数类型、返回类型等属性。应用场景包括插入单个对象、批量插入等。以下是一个插入单个对象并返回自动生成的主键的示例：

```
<insert id="insertUser" parameterType="User" useGeneratedKeys="true" keyProperty="id">
    INSERT INTO users (name, age) VALUES (#{name}, #{age})
</insert>
```

3. `<update>`标签：用于执行UPDATE语句并返回受影响的行数。该标签可以指定SQL语句、参数类型等属性。应用场景包括更新单个对象、批量更新等。以下是一个更新单个对象的示例：

```
<update id="updateUser" parameterType="User">
    UPDATE users SET name = #{name}, age = #{age} WHERE id = #{id}
</update>
```

4. `<delete>`标签：用于执行DELETE语句并返回受影响的行数。该标签可以指定SQL语句、参数类型等属性。应用场景包括删除单个对象、批量删除等。以下是一个删除单个对象的示例：

```
<delete id="deleteUser" parameterType="int">
    DELETE FROM users WHERE id = #{id}
</delete>
```

5. `<resultMap>`标签：用于将查询结果映射为Java对象。该标签可以指定Java类型、SQL列名、Java属性名等属性。应用场景包括复杂查询、多表关联查询等。以下是一个多表关联查询的示例：

```
<resultMap id="userAddressMap" type="User">
    <id property="id" column="user_id"/>
    <result property="name" column="user_name"/>
    <result property="age" column="user_age"/>
    <collection property="addresses" ofType="Address">
        <id property="id" column="address_id"/>
        <result property="street" column="address_street"/>
        <result property="city" column="address_city"/>
    </collection>
</resultMap>

<select id="getUserByIdWithAddress" parameterType="int" resultMap="userAddressMap">
    SELECT u.id AS user_id, u.name AS user_name, u.age AS user_age, a.id AS address_id, a.street AS address_street, a.city AS address_city
    FROM users u JOIN addresses a ON u.id = a.user_id
    WHERE u.id = #{id}
</select>
```

6. `<if>`标签：用于根据条件动态生成SQL语句。该标签可以指定test属性，表示条件表达式。应用场景包括动态查询、动态更新、动态删除等。以下是一个动态查询的示例：

```
<select id="getUserList" parameterType="User" resultType="User">
    SELECT * FROM users
    <where>
        <if test="name != null">
            AND name = #{name}
        </if>
        <if test="age != null">
            AND age = #{age}
        </if>
    </where>
</select>
```

7. `<foreach>`标签：用于遍历集合或数组，并动态生成SQL语句。该标签可以指定collection属性，表示要遍历的集合或数组，以及item属性，表示遍历的元素。应用场景包括批量插入、批量更新、批量删除等。以下是一个批量插入的示例：

```
<insert id="batchInsertUser" parameterType="List">
    INSERT INTO users (name, age) VALUES
    <foreach collection="list" item="user" separator=",">
        (#{user.name}, #{user.age})
    </foreach>
</insert>
```

以上是一些常用的MyBatis标签及其应用场景和测试案例，仅供参考。MyBatis还提供了许多其他标签和功能，具体使用取决于具体的业务场景和需求。

在 MyBatis 中，`resultMap` 元素用于定义如何将 SQL 查询结果映射到 Java 对象。`resultMap` 元素包含多个子元素，其中一个是 `result` 元素，它用于定义单个属性或字段的映射。`result` 元素有一个名为 `select` 的属性，它指定了要使用的 SQL 语句的 ID。

当使用 `select` 属性时，MyBatis 将执行指定 ID 的 SQL 语句，并将结果映射到 Java 对象的属性或字段。这通常用于在一个 `resultMap` 中映射多个表的数据，或者在一个 `resultMap` 中映射复杂的对象关系。

例如，假设我们有两个表：`user` 和 `address`，并且它们之间有一个外键关系。我们可以创建一个 `resultMap`，其中包含两个 `result` 元素，一个用于映射 `user` 表，另一个用于映射 `address` 表。在 `address` 表的 `result` 元素中，我们可以使用 `select` 属性来指定一个查询，该查询将返回与当前用户关联的所有地址。

以下是一个示例：

```xml
<resultMap id="userResultMap" type="User">
  <id property="id" column="user_id"/>
  <result property="username" column="username"/>
  <result property="email" column="email"/>

  <result property="addresses" resultMap="addressResultMap"/>
</resultMap>

<resultMap id="addressResultMap" type="Address">
  <id property="id" column="address_id"/>
  <result property="street" column="street"/>
  <result property="city" column="city"/>
  <result property="state" column="state"/>
  <result property="zip" column="zip"/>

  <association property="user" javaType="User">
    <id property="id" column="user_id"/>
    <result property="username" column="username"/>
    <result property="email" column="email"/>
  </association>

  <select id="findAddressesByUserId" resultType="Address">
    SELECT * FROM address WHERE user_id = #{id}
  </select>
</resultMap>
```

在上面的示例中，`userResultMap` 包含一个名为 `addresses` 的属性，它映射到 `User` 类中的一个 `List<Address>`。在 `addressResultMap` 中，我们定义了一个名为 `findAddressesByUserId` 的查询，它将返回与当前用户关联的所有地址。在 `addresses` 属性的 `result` 元素中，我们使用 `select` 属性来指定这个查询。这将导致 MyBatis 在映射 `User` 对象时执行这个查询，并将结果映射到 `addresses` 属性。