-- ������
DELETE FROM CATEGORY_HAS_KNOWLEDGE;
DELETE FROM CATEGORY;
DELETE FROM KNOWLEDGE_COMMENT;
DELETE FROM KNOWLEDGE;
DELETE FROM ACCOUNT;

-- �J�e�S���\
INSERT INTO CATEGORY (ID, NAME) VALUES (1, 'Java');
INSERT INTO CATEGORY (ID, NAME) VALUES (2, 'DB');
INSERT INTO CATEGORY (ID, NAME) VALUES (3, 'FMW');

-- �A�J�E���g�\
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  1,
  'adminGroup',
  'ichiro.yamada@example.com',
  '�R�c��Y',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'ichiro'
);
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  2,
  'userGroup',
  'jiro.tanaka@example.com',
  '�c����Y',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'jiro'
);
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  3,
  'userGroup',
  'hanako.suzuki@example.com',
  '��؉Ԏq',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'hanako'
);

-- �i���b�W�\
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  1,
  to_timestamp('2015/05/24 22:28:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('https://blogs.oracle.com/otnjp/entry/oracle_database_12c_portal'), 
  '�f�[�^�x�[�X�������߃����N�W',
  to_timestamp('2015/05/24 22:28:24','yyyy/mm/dd hh24:mi:ss'),
  2,
  to_timestamp('2015/05/24 22:28:24','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  2,
  to_timestamp('2015/05/24 22:41:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('java�R�}���h���s���Ɉȉ��̃p�����[�^���w�肵�܂��B') ||
    CHR(13) || CHR(10) ||
    CHR(13) || CHR(10) ||
    to_clob('-Xloggc:/var/log/gc.log //���O�o�͐�') ||
    CHR(13) || CHR(10) ||
    to_clob('-XX:+PrintGCDetails //�ڍׂȃ��O���o��') ||
    CHR(13) || CHR(10) ||
    to_clob('-XX:+PrintGCDateStamps //�^�C���X�^���v���o��'), 
  'GC���O�̏o�͕��@',
  to_timestamp('2015/05/24 22:41:24','yyyy/mm/dd hh24:mi:ss'),
  3,
  to_timestamp('2015/05/24 22:41:24','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  3,
  to_timestamp('2015/05/24 22:42:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('�wlambda�x�́w�����_�x�Ɠǂ݂܂��B�ڍׂ͟N��S�ꂳ��́w����Ŏg����[�ŐV]Java SE 7/8 ���U����x�Ƃ������Ђ��ڂ����ł��B'), 
  'Java��lambda���ɂ���',
  to_timestamp('2015/05/24 22:43:24','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/05/24 22:42:24','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  4,
  to_timestamp('2015/05/24 22:44:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('�ڍׂ́wJava EE 7�O�����x�Ƃ������Ђ��ڂ����ł��B'),
  'JPA�̐ݒ�ƃ`���[�j���O',
  to_timestamp('2015/05/24 22:44:24','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/05/24 22:44:24','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  5,
  to_timestamp('2015/05/24 22:54:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('Java EE��1999�N�Ɏd�l�������[�X����A����Java EE 7���ŐV�ł��B') ||
    CHR(13) || CHR(10) ||
    CHR(13) || CHR(10) ||
    to_clob('J2EE 1.2�F1999�N12��') ||
    CHR(13) || CHR(10) ||
    to_clob('J2EE 1.3�F2001�N9��') ||
    CHR(13) || CHR(10) ||
    to_clob('J2EE 1.4�F2003�N11��') ||
    CHR(13) || CHR(10) ||
    to_clob('Java EE 5�F2006�N5��') ||
    CHR(13) || CHR(10) ||
    to_clob('Java EE 6�F2009�N12��') ||
    CHR(13) || CHR(10) ||
    to_clob('Java EE 7�F2013�N5��'),
  'Java EE�̃����[�X',
  to_timestamp('2015/05/24 23:05:24','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/05/24 22:54:24','yyyy/mm/dd hh24:mi:ss')
);

-- �J�e�S��-�i���b�W�\
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (1, 2);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (2, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (3, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (4, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (4, 2);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (5, 1);

-- �R�����g�\
INSERT INTO KNOWLEDGE_COMMENT (ID, CREATE_AT, MESSAGE, UPDATE_AT, ACCOUNT_ID, KNOWLEDGE_ID)
VALUES (
  1, 
  to_timestamp('2015/05/24 23:09:24','yyyy/mm/dd hh24:mi:ss'),
  to_clob('Java EE 8��2016�N�Ƀ����[�X�\��ł��B'), 
  to_timestamp('2015/05/24 23:09:24','yyyy/mm/dd hh24:mi:ss'),
  1,
  5
);
commit;
-- exit;
