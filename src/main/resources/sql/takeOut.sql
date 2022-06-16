create table address
(
    id          bigint auto_increment comment 'id'
        primary key,
    cust_id     varchar(32)      not null comment '客户编号',
    contact     varchar(20)      not null comment '联系人',
    tel         varchar(11)      not null comment '联系电话',
    address     varchar(32)      not null comment '配送地址',
    is_default  char             null comment '是否默认地址',
    delete_yn   char default '0' not null comment '删除标记',
    create_time datetime         null comment '创建时间',
    update_time datetime         null comment '更新时间',
    create_id   varchar(32)      null comment '创建人编号',
    update_id   varchar(32)      null comment '更新人编号'
)
    comment '客户地址表';

create table cust
(
    id          bigint auto_increment comment 'id'
        primary key,
    cust_id     varchar(32)      not null comment '客户编号',
    user_name   varchar(32)      not null comment '用户名',
    name        varchar(10)      not null comment '姓名',
    tel         varchar(11)      not null comment '手机号',
    regist_time datetime         not null comment '注册时间',
    delete_yn   char default '0' not null comment '默认：0（未删除），删除后改为：1（已删除）',
    create_time datetime         null comment '创建时间',
    update_time datetime         null comment '更新时间',
    create_id   varchar(32)      null comment '创建人编号',
    update_id   varchar(32)      null comment '更新人编号'
)
    comment '客户表';

create table delivery
(
    id               bigint auto_increment comment 'id'
        primary key,
    primary_order_id varchar(32)      not null comment '主订单编号',
    order_id         varchar(32)      not null comment '订单编号',
    contact          varchar(20)      not null comment '联系人',
    tel              varchar(11)      not null comment '联系电话',
    address          varchar(32)      not null comment '配送地址',
    remark           varchar(512)     null comment '配送备注',
    delete_yn        char default '0' not null comment '删除标记',
    create_time      datetime         null comment '创建时间',
    update_time      datetime         null comment '更新时间',
    create_id        varchar(32)      null comment '创建人编号',
    update_id        varchar(32)      null comment '更新人编号'
)
    comment '订单配送表';

create table item
(
    id           bigint auto_increment comment 'id'
        primary key,
    shop_id      varchar(32)             not null comment '商户编号',
    item_id      varchar(32)             not null comment '商品编号',
    item_name    varchar(32)             not null comment '商品名称',
    item_img_url varchar(32)             null comment '商品图片',
    price        varchar(32)             not null comment '商品价格（分）',
    make_time    varchar(32)             not null comment '制作时长（分）',
    item_type    varchar(11) default '1' not null comment '商品类型：1 - 实物商品，2 - 配送费',
    item_desc    varchar(512)            null comment '商品描述',
    sell_count   int         default 0   null comment '销售量',
    delete_yn    char        default '0' not null comment '删除标记',
    create_time  datetime                null comment '创建时间',
    update_time  datetime                null comment '更新时间',
    create_id    varchar(32)             null comment '创建人编号',
    update_id    varchar(32)             null comment '更新人编号'
)
    comment '储存商户上架的商品信息';

create table pay
(
    id                 bigint auto_increment comment 'id'
        primary key,
    primary_order_flag char             not null comment '主订单标记：0 - 子单，1 - 主单',
    primary_order_id   varchar(32)      not null comment '主订单编号',
    order_id           varchar(32)      not null comment '订单编号',
    pay_status         char default '0' not null comment '支付状态：0 - 待支付，1 - 已支付',
    pay_method         char             not null comment '支付方式: 1 - 支付宝，2 - 微信',
    pay_amount         varchar(19)      not null comment '支付金额（分）',
    pay_time           datetime         not null comment '支付时间',
    delete_yn          char default '0' not null comment '删除标记',
    create_time        datetime         null comment '创建时间',
    update_time        datetime         null comment '更新时间',
    create_id          varchar(32)      null comment '创建人编号',
    update_id          varchar(32)      null comment '更新人编号'
)
    comment '支付表';

create table permission
(
    permission_id   int          not null
        primary key,
    permission      varchar(255) null,
    permission_name varchar(255) null
);

INSERT INTO shirodemo.permission (permission_id, permission, permission_name) VALUES (1, 'select', '查看');
INSERT INTO shirodemo.permission (permission_id, permission, permission_name) VALUES (2, 'update', '更新');
INSERT INTO shirodemo.permission (permission_id, permission, permission_name) VALUES (3, 'delete', '删除');
INSERT INTO shirodemo.permission (permission_id, permission, permission_name) VALUES (4, 'save', '新增');

create table refund
(
    id            bigint auto_increment comment 'id'
        primary key,
    reversal_id   varchar(32)      not null comment '售后单编号',
    refund_status char             not null comment '订单状态：0 - 待退款，1 - 已退款',
    refund_fee    varchar(11)      not null comment '退款金额（分）',
    refund_time   datetime         not null comment '退款时间',
    delete_yn     char default '0' not null comment '删除标记',
    create_time   datetime         null comment '创建时间',
    update_time   datetime         null comment '更新时间',
    create_id     varchar(32)      null comment '创建人编号',
    update_id     varchar(32)      null comment '更新人编号'
)
    comment '售后退款表';

create table reversal
(
    id                 bigint auto_increment comment 'id'
        primary key,
    primary_order_flag char             not null comment '主订单标记：0 - 子单，1 - 主单',
    primary_order_id   varchar(32)      not null comment '主订单编号',
    order_id           varchar(32)      not null comment '订单编号',
    order_status       char             not null comment '订单状态：0 - 待支付，1 - 已支付，2 - 制作中，3 - 制作完成，4 - 已取餐，5 - 配送中，6 - 订单完成，7 - 申请撤单，8 - 订单取消',
    cust_id            varchar(32)      not null comment '客户编号',
    shop_id            varchar(32)      not null comment '商户编号',
    item_id            varchar(32)      not null comment '商品编号',
    item_quantity      int              not null comment '商品数量',
    remark             varchar(512)     null comment '订单备注',
    delete_yn          char default '0' not null comment '删除标记',
    create_time        datetime         null comment '创建时间',
    update_time        datetime         null comment '更新时间',
    create_id          varchar(32)      null comment '创建人编号',
    update_id          varchar(32)      null comment '更新人编号'
)
    comment '售后单表';

create table role
(
    role_id   int          not null
        primary key,
    role_name varchar(255) null
);

INSERT INTO shirodemo.role (role_id, role_name) VALUES (1, 'svip');
INSERT INTO shirodemo.role (role_id, role_name) VALUES (2, 'vip');
INSERT INTO shirodemo.role (role_id, role_name) VALUES (3, 'p');

create table role_permission
(
    role_id       int not null,
    permission_id int not null,
    primary key (role_id, permission_id),
    constraint FKa6jx8n8xkesmjmv6jqug6bg68
        foreign key (role_id) references role (role_id),
    constraint FKf8yllw1ecvwqy3ehyxawqa1qp
        foreign key (permission_id) references permission (permission_id)
);

INSERT INTO shirodemo.role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO shirodemo.role_permission (role_id, permission_id) VALUES (2, 1);
INSERT INTO shirodemo.role_permission (role_id, permission_id) VALUES (3, 1);
INSERT INTO shirodemo.role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO shirodemo.role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO shirodemo.role_permission (role_id, permission_id) VALUES (1, 3);
INSERT INTO shirodemo.role_permission (role_id, permission_id) VALUES (1, 4);
INSERT INTO shirodemo.role_permission (role_id, permission_id) VALUES (2, 4);


create table shop
(
    id           bigint auto_increment comment 'id'
        primary key,
    shop_id      varchar(32)      not null comment '商户编号',
    shop_name    varchar(32)      not null comment '商户名称',
    user_name    varchar(32)      not null comment '用户名',
    user_type    char             not null,
    shop_tel     varchar(11)      not null comment '联系电话',
    shop_address varchar(128)     null comment '店铺地址',
    shop_img_url varchar(32)      null comment '店铺图片',
    sell_count   int  default 0   null comment '销售量',
    shop_desc    varchar(512)     null comment '店铺描述',
    delete_yn    char default '0' not null comment '删除标记',
    create_time  datetime         null comment '创建时间',
    update_time  datetime         null comment '更新时间',
    create_id    varchar(32)      null comment '创建人编号',
    update_id    varchar(32)      null comment '更新人编号',
    is_effect    char default '0' null
)
    comment '存储商户信息及管理员信息的表';

create table sys_code
(
    id                 bigint auto_increment comment '自增id'
        primary key,
    par_code_type_id   varchar(128)         not null comment '参数编码类型：标识参数的唯一业务编码',
    par_code_type_name varchar(128)         not null comment '参数编码类型名称',
    par_biz_code       varchar(128)         not null comment '参数业务编码',
    par_biz_name       varchar(256)         not null comment '参数名称',
    par_super_biz_code varchar(128)         null comment '上级参数业务编码',
    delete_yn          tinyint(1) default 0 not null comment '有效性标志：0-无效，1-有效',
    create_time        datetime             not null comment '记录创建时间',
    update_time        datetime             not null comment '记录最后更新时间，记录初次创建时等同于create_time',
    create_id          varchar(32)          null comment '记录创建人id',
    update_id          varchar(32)          null comment '记录最后更新人id，记录初次创建等于create_id'
)
    comment '系统参数表';

create table sys_token
(
    user_id     int          not null
        primary key,
    expire_time datetime     null,
    token       varchar(255) null,
    update_time datetime     null
);

create table t_order
(
    id                 bigint auto_increment comment 'id'
        primary key,
    primary_order_flag char             not null comment '主订单标记：0 - 子单，1 - 主单',
    primary_order_id   varchar(32)      not null comment '主订单编号',
    order_id           varchar(32)      not null comment '订单编号',
    order_name         varchar(256)     null comment '订单名称',
    order_imgs         varchar(512)     null comment '订单图片',
    order_type         char             not null comment '订单类型：1-到店自取，2-商家配送',
    order_status       char             not null comment '订单状态：0 - 待支付，1 - 已支付，2 - 制作中，3 - 制作完成，4 - 已取餐，5 - 配送中，6 - 订单完成，7 - 申请撤单，8 - 订单取消',
    cust_id            varchar(32)      not null comment '客户编号',
    shop_id            varchar(32)      not null comment '商户编号',
    item_id            varchar(32)      not null comment '商品编号',
    item_quantity      int              not null comment '商品数量',
    total_fee          varchar(11)      not null comment '订单总金额（分）',
    address            varchar(256)     null,
    remark             varchar(512)     null comment '订单备注',
    delete_yn          char default '0' not null comment '删除标记',
    create_time        datetime         null comment '创建时间',
    update_time        datetime         null comment '更新时间',
    create_id          varchar(32)      null comment '创建人编号',
    update_id          varchar(32)      null comment '更新人编号',
    do_time            bigint           null comment '倒计时时间戳'
)
    comment '订单表';

create table user
(
    user_id  int          not null
        primary key,
    password varchar(255) null,
    username varchar(255) null
);

INSERT INTO shirodemo.user (user_id, password, username) VALUES (1, '123', 'Jack');

create table user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint FK859n2jvi8ivhui0rl0esws6o
        foreign key (user_id) references user (user_id),
    constraint FKa68196081fvovjhkek5m97n3y
        foreign key (role_id) references role (role_id)
);

INSERT INTO shirodemo.user_role (user_id, role_id) VALUES (1, 1);