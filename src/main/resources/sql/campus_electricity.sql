DROP SCHEMA IF EXISTS campus_electricity;
CREATE SCHEMA campus_electricity;
USE campus_electricity;

# 用户表
CREATE TABLE IF NOT EXISTS user
(
    id         UUID PRIMARY KEY NOT NULL,

    username   VARCHAR(32)      NOT NULL COMMENT '用户名',
    password   VARCHAR(128)     NOT NULL COMMENT '密码',

    name       VARCHAR(64)      NOT NULL COMMENT '姓名',
    email      VARCHAR(64)      NOT NULL COMMENT '邮箱',
    phone      VARCHAR(16)      NOT NULL COMMENT '手机号',
    room_id    UUID             NOT NULL COMMENT '房间 ID',
    student_no VARCHAR(16)      NOT NULL COMMENT '学号',
    role       VARCHAR(16)      NOT NULL COMMENT '角色',

    created_at DATETIME         NOT NULL DEFAULT NOW(),
    updated_at DATETIME         NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    created_by UUID             NULL,
    updated_by UUID             NULL,
    is_deleted BOOLEAN          NOT NULL DEFAULT FALSE
) ENGINE = InnoDB
  CHARSET = UTF8MB4
    COMMENT = '用户表';

# 用户权限表
CREATE TABLE IF NOT EXISTS user_permission
(
    id         UUID PRIMARY KEY NOT NULL,

    user_id    UUID             NOT NULL COMMENT '用户ID',
    permission VARCHAR(128)     NOT NULL COMMENT '权限',

    created_at DATETIME         NOT NULL DEFAULT NOW(),
    updated_at DATETIME         NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    created_by UUID             NULL,
    updated_by UUID             NULL,
    is_deleted BOOLEAN          NOT NULL DEFAULT FALSE
) ENGINE = InnoDB
  CHARSET = UTF8MB4
    COMMENT = '用户权限表';

# 用电记录表
CREATE TABLE IF NOT EXISTS electricity_record
(
    id          UUID PRIMARY KEY NOT NULL,

    room_id     UUID             NOT NULL COMMENT '房间 ID',
    electricity DOUBLE           NOT NULL COMMENT '用电量',
    cost        DOUBLE           NOT NULL COMMENT '费用',
    start_time  DATETIME         NOT NULL COMMENT '开始时间',
    end_time    DATETIME         NOT NULL COMMENT '结束时间',

    created_at  DATETIME         NOT NULL DEFAULT NOW(),
    updated_at  DATETIME         NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    created_by  UUID             NULL,
    updated_by  UUID             NULL,
    is_deleted  BOOLEAN          NOT NULL DEFAULT FALSE
) ENGINE = InnoDB
  CHARSET = UTF8MB4
    COMMENT = '用电记录表';

# 电费缴费记录表
CREATE TABLE IF NOT EXISTS payment_record
(
    id           UUID PRIMARY KEY NOT NULL,

    user_id      UUID             NOT NULL COMMENT '用户ID',
    cost         DOUBLE           NOT NULL COMMENT '费用',
    payment_time DATETIME         NOT NULL COMMENT '缴费时间',

    created_at   DATETIME         NOT NULL DEFAULT NOW(),
    updated_at   DATETIME         NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    created_by   UUID             NULL,
    updated_by   UUID             NULL,
    is_deleted   BOOLEAN          NOT NULL DEFAULT FALSE
) ENGINE = InnoDB
  CHARSET = UTF8MB4
    COMMENT = '电费缴费记录表';

# 缴费通知单
CREATE TABLE IF NOT EXISTS payment_notice
(
    id         UUID PRIMARY KEY NOT NULL,

    user_id    UUID             NOT NULL COMMENT '用户ID',
    cost       DOUBLE           NOT NULL COMMENT '费用',
    due_date   DATE             NOT NULL COMMENT '缴费截止时间',
    status     VARCHAR(16)      NOT NULL COMMENT '状态',

    created_at DATETIME         NOT NULL DEFAULT NOW(),
    updated_at DATETIME         NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    created_by UUID             NULL,
    updated_by UUID             NULL,
    is_deleted BOOLEAN          NOT NULL DEFAULT FALSE
) ENGINE = InnoDB
  CHARSET = UTF8MB4
    COMMENT = '缴费通知单';

# 楼栋表
CREATE TABLE IF NOT EXISTS building
(
    id         UUID PRIMARY KEY NOT NULL,

    name       VARCHAR(32)      NOT NULL COMMENT '楼栋名称',

    created_at DATETIME         NOT NULL DEFAULT NOW(),
    updated_at DATETIME         NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    created_by UUID             NULL,
    updated_by UUID             NULL,
    is_deleted BOOLEAN          NOT NULL DEFAULT FALSE
) ENGINE = InnoDB
  CHARSET = UTF8MB4 COMMENT = '楼栋表';

# 房间表
CREATE TABLE IF NOT EXISTS room
(
    id          UUID PRIMARY KEY NOT NULL,

    building_id UUID             NOT NULL COMMENT '楼栋ID',
    name        VARCHAR(16)      NOT NULL COMMENT '房间号',

    created_at  DATETIME         NOT NULL DEFAULT NOW(),
    updated_at  DATETIME         NOT NULL DEFAULT NOW() ON UPDATE NOW(),
    created_by  UUID             NULL,
    updated_by  UUID             NULL,
    is_deleted  BOOLEAN          NOT NULL DEFAULT FALSE
) ENGINE = InnoDB
  CHARSET = UTF8MB4 COMMENT = '房间表';
