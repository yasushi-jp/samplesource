-- 初期化
DELETE FROM CATEGORY_HAS_KNOWLEDGE;
DELETE FROM CATEGORY;
DELETE FROM KNOWLEDGE_COMMENT;
DELETE FROM KNOWLEDGE;
DELETE FROM ACCOUNT;

-- カテゴリ表
INSERT INTO CATEGORY (ID, NAME) VALUES (1, 'Java');
INSERT INTO CATEGORY (ID, NAME) VALUES (2, 'エンタープライズ');
INSERT INTO CATEGORY (ID, NAME) VALUES (3, 'チューニング');

-- アカウント表
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  1,
  'userGroup',
  'vvv@yyy.zz',
  'いとうちひろ',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'chihiro.i'
);
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  2,
  'userGroup',
  NULL,
  'てらだよしお',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'yoshio.t'
);

-- ナレッジ表
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  1,
  to_timestamp('2015/06/29 14:30:12','yyyy/mm/dd hh24:mi:ss'),
  to_clob('JavaEEアプリケーションを実行するには、GlassFishなどJavaEEに準拠したアプリケーションサーバが必要です。'), 
  'JavaEEの実行環境',
  to_timestamp('2015/07/01 16:00:34','yyyy/mm/dd hh24:mi:ss'),
  2,
  to_timestamp('2015/07/03 16:00:34','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  2,
  to_timestamp('2015/06/30 09:00:12','yyyy/mm/dd hh24:mi:ss'),
  to_clob('Javaアプリを起動するには、javaコマンドを利用します。CLASSPATH変数が正しく設定されている必要があります。'), 
  'Javaアプリの起動方法',
  to_timestamp('2015/07/02 10:30:34','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/06/30 09:00:12','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  3,
  to_timestamp('2015/06/30 13:00:12','yyyy/mm/dd hh24:mi:ss'),
  to_clob('エンタープライズ向けのJVMは、長時間にわたり安定した性能が出せるようにチューニングしなければなりません。逆に、不安定な状態に陥った時は、その状態が周りに伝搬しないようにすることも大事です。例えばOutOfMemoryErrorが発生した場合、その後の挙動は保証されないのがJavaの仕様ですので、起動オプションに"-XX:OnOutOfMemoryError="kill -9 %p"を指定して、すぐにJVMを停止するのが良いでしょう。'), 
  'エンタープライズJavaチューニング',
  to_timestamp('2015/07/03 15:00:34','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/06/30 13:00:12','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  4,
  to_timestamp('2015/06/30 18:30:12','yyyy/mm/dd hh24:mi:ss'),
  to_clob('Javaアプリを起動するには、javaコマンドを利用します。CLASSPATH変数が正しく設定されている必要があります。'), 
  'Javaアプリの起動方法',
  to_timestamp('2015/07/02 16:00:34','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/06/30 18:30:12','yyyy/mm/dd hh24:mi:ss')
);

-- カテゴリ-ナレッジ表
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (1, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (1, 2);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (2, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (3, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (3, 2);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (3, 3);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (4, 3);

-- コメント表
INSERT INTO KNOWLEDGE_COMMENT (ID, CREATE_AT, MESSAGE, UPDATE_AT, ACCOUNT_ID, KNOWLEDGE_ID)
VALUES (
  1, 
  to_timestamp('2015/07/02 12:34:56','yyyy/mm/dd hh24:mi:ss'),
  to_clob('WebLogicも良いですよ'), 
  to_timestamp('2015/07/02 12:34:56','yyyy/mm/dd hh24:mi:ss'),
  1,
  1
);
INSERT INTO KNOWLEDGE_COMMENT (ID, CREATE_AT, MESSAGE, UPDATE_AT, ACCOUNT_ID, KNOWLEDGE_ID)
VALUES (
  2, 
  to_timestamp('2015/07/03 16:00:34','yyyy/mm/dd hh24:mi:ss'),
  to_clob('ありがとうございます！'), 
  to_timestamp('2015/07/03 16:00:34','yyyy/mm/dd hh24:mi:ss'),
  2,
  1
);
commit;
-- exit;
