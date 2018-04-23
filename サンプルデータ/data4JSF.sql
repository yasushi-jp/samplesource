-- 初期化
DELETE FROM CATEGORY_HAS_KNOWLEDGE;
DELETE FROM CATEGORY;
DELETE FROM KNOWLEDGE_COMMENT;
DELETE FROM KNOWLEDGE;
DELETE FROM ACCOUNT;

-- カテゴリ表
INSERT INTO CATEGORY (ID, NAME) VALUES (1, 'Java');
INSERT INTO CATEGORY (ID, NAME) VALUES (2, 'DB');
INSERT INTO CATEGORY (ID, NAME) VALUES (3, 'FMW');

-- アカウント表
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  1,
  'adminGroup',
  'ichiro.yamada@example.com',
  '山田一郎',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'ichiro'
);
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  2,
  'userGroup',
  'jiro.tanaka@example.com',
  '田中二郎',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'jiro'
);
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  3,
  'userGroup',
  'hanako.suzuki@example.com',
  '鈴木花子',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'hanako'
);

-- ナレッジ表
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  1,
  to_timestamp('2015/05/24 22:28:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('https://blogs.oracle.com/otnjp/entry/oracle_database_12c_portal'), 
  'データベースおすすめリンク集',
  to_timestamp('2015/05/24 22:28:24','yyyy/mm/dd hh24:mi:ss'),
  2,
  to_timestamp('2015/05/24 22:28:24','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  2,
  to_timestamp('2015/05/24 22:41:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('javaコマンド実行時に以下のパラメータを指定します。') ||
    CHR(13) || CHR(10) ||
    CHR(13) || CHR(10) ||
    to_clob('-Xloggc:/var/log/gc.log //ログ出力先') ||
    CHR(13) || CHR(10) ||
    to_clob('-XX:+PrintGCDetails //詳細なログを出力') ||
    CHR(13) || CHR(10) ||
    to_clob('-XX:+PrintGCDateStamps //タイムスタンプを出力'), 
  'GCログの出力方法',
  to_timestamp('2015/05/24 22:41:24','yyyy/mm/dd hh24:mi:ss'),
  3,
  to_timestamp('2015/05/24 22:41:24','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  3,
  to_timestamp('2015/05/24 22:42:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('『lambda』は『ラムダ』と読みます。詳細は櫻庭祐一さんの『現場で使える[最新]Java SE 7/8 速攻入門』という書籍が詳しいです。'), 
  'Javaのlambda式について',
  to_timestamp('2015/05/24 22:43:24','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/05/24 22:42:24','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  4,
  to_timestamp('2015/05/24 22:44:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('詳細は『Java EE 7徹底入門』という書籍が詳しいです。'),
  'JPAの設定とチューニング',
  to_timestamp('2015/05/24 22:44:24','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/05/24 22:44:24','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  5,
  to_timestamp('2015/05/24 22:54:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('Java EEは1999年に仕様がリリースされ、現在Java EE 7が最新です。') ||
    CHR(13) || CHR(10) ||
    CHR(13) || CHR(10) ||
    to_clob('J2EE 1.2：1999年12月') ||
    CHR(13) || CHR(10) ||
    to_clob('J2EE 1.3：2001年9月') ||
    CHR(13) || CHR(10) ||
    to_clob('J2EE 1.4：2003年11月') ||
    CHR(13) || CHR(10) ||
    to_clob('Java EE 5：2006年5月') ||
    CHR(13) || CHR(10) ||
    to_clob('Java EE 6：2009年12月') ||
    CHR(13) || CHR(10) ||
    to_clob('Java EE 7：2013年5月'),
  'Java EEのリリース',
  to_timestamp('2015/05/24 23:05:24','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/05/24 22:54:24','yyyy/mm/dd hh24:mi:ss')
);

-- カテゴリ-ナレッジ表
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (1, 2);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (2, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (3, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (4, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (4, 2);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (5, 1);

-- コメント表
INSERT INTO KNOWLEDGE_COMMENT (ID, CREATE_AT, MESSAGE, UPDATE_AT, ACCOUNT_ID, KNOWLEDGE_ID)
VALUES (
  1, 
  to_timestamp('2015/05/24 23:09:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('Java EE 8は2016年にリリース予定です。'), 
  to_timestamp('2015/05/24 23:09:24','yyyy/mm/dd hh24:mi:ss'),
  1,
  5
);
commit;
-- exit;
