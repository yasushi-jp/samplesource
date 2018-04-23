-- ������
DELETE FROM CATEGORY_HAS_KNOWLEDGE;
DELETE FROM CATEGORY;
DELETE FROM KNOWLEDGE_COMMENT;
DELETE FROM KNOWLEDGE;
DELETE FROM ACCOUNT;

-- �J�e�S���\
INSERT INTO CATEGORY (ID, NAME) VALUES (1, 'Java');
INSERT INTO CATEGORY (ID, NAME) VALUES (2, '�G���^�[�v���C�Y');
INSERT INTO CATEGORY (ID, NAME) VALUES (3, '�`���[�j���O');

-- �A�J�E���g�\
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  1,
  'userGroup',
  'vvv@yyy.zz',
  '���Ƃ����Ђ�',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'chihiro.i'
);
INSERT INTO ACCOUNT (ID, ACCOUNT_GROUP, MAIL, NAME, PASSWORD, USER_ID) 
VALUES (
  2,
  'userGroup',
  NULL,
  '�Ă炾�悵��',
  '1ea4b2440d31512b05cc5e55486d627559ed2fa693a14e16212dc80d68c392fa',
  'yoshio.t'
);

-- �i���b�W�\
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  1,
  to_timestamp('2015/06/29 14:30:12','yyyy/mm/dd hh24:mi:ss'),
  to_clob('JavaEE�A�v���P�[�V���������s����ɂ́AGlassFish�Ȃ�JavaEE�ɏ��������A�v���P�[�V�����T�[�o���K�v�ł��B'), 
  'JavaEE�̎��s��',
  to_timestamp('2015/07/01 16:00:34','yyyy/mm/dd hh24:mi:ss'),
  2,
  to_timestamp('2015/07/03 16:00:34','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  2,
  to_timestamp('2015/06/30 09:00:12','yyyy/mm/dd hh24:mi:ss'),
  to_clob('Java�A�v�����N������ɂ́Ajava�R�}���h�𗘗p���܂��BCLASSPATH�ϐ����������ݒ肳��Ă���K�v������܂��B'), 
  'Java�A�v���̋N�����@',
  to_timestamp('2015/07/02 10:30:34','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/06/30 09:00:12','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  3,
  to_timestamp('2015/06/30 13:00:12','yyyy/mm/dd hh24:mi:ss'),
  to_clob('�G���^�[�v���C�Y������JVM�́A�����Ԃɂ킽����肵�����\���o����悤�Ƀ`���[�j���O���Ȃ���΂Ȃ�܂���B�t�ɁA�s����ȏ�ԂɊׂ������́A���̏�Ԃ�����ɓ`�����Ȃ��悤�ɂ��邱�Ƃ��厖�ł��B�Ⴆ��OutOfMemoryError�����������ꍇ�A���̌�̋����͕ۏ؂���Ȃ��̂�Java�̎d�l�ł��̂ŁA�N���I�v�V������"-XX:OnOutOfMemoryError="kill -9 %p"���w�肵�āA������JVM���~����̂��ǂ��ł��傤�B'), 
  '�G���^�[�v���C�YJava�`���[�j���O',
  to_timestamp('2015/07/03 15:00:34','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/06/30 13:00:12','yyyy/mm/dd hh24:mi:ss')
);
INSERT INTO KNOWLEDGE (ID, CREATE_AT, DESCRIPTION, TITLE, UPDATE_AT, ACCOUNT_ID, LAST_COMMENT_AT)
VALUES (
  4,
  to_timestamp('2015/06/30 18:30:12','yyyy/mm/dd hh24:mi:ss'),
  to_clob('Java�A�v�����N������ɂ́Ajava�R�}���h�𗘗p���܂��BCLASSPATH�ϐ����������ݒ肳��Ă���K�v������܂��B'), 
  'Java�A�v���̋N�����@',
  to_timestamp('2015/07/02 16:00:34','yyyy/mm/dd hh24:mi:ss'),
  1,
  to_timestamp('2015/06/30 18:30:12','yyyy/mm/dd hh24:mi:ss')
);

-- �J�e�S��-�i���b�W�\
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (1, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (1, 2);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (2, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (3, 1);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (3, 2);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (3, 3);
INSERT INTO CATEGORY_HAS_KNOWLEDGE (KNOWLEDGELIST_ID, CATEGORYLIST_ID) VALUES (4, 3);

-- �R�����g�\
INSERT INTO KNOWLEDGE_COMMENT (ID, CREATE_AT, MESSAGE, UPDATE_AT, ACCOUNT_ID, KNOWLEDGE_ID)
VALUES (
  1, 
  to_timestamp('2015/07/02 12:34:56','yyyy/mm/dd hh24:mi:ss'),
  to_clob('WebLogic���ǂ��ł���'), 
  to_timestamp('2015/07/02 12:34:56','yyyy/mm/dd hh24:mi:ss'),
  1,
  1
);
INSERT INTO KNOWLEDGE_COMMENT (ID, CREATE_AT, MESSAGE, UPDATE_AT, ACCOUNT_ID, KNOWLEDGE_ID)
VALUES (
  2, 
  to_timestamp('2015/07/03 16:00:34','yyyy/mm/dd hh24:mi:ss'),
  to_clob('���肪�Ƃ��������܂��I'), 
  to_timestamp('2015/07/03 16:00:34','yyyy/mm/dd hh24:mi:ss'),
  2,
  1
);
commit;
-- exit;
