create table FORMULA (FORMULA_DESCRIPTION_ID number(10,0) not null, ID number(10,0) not null, VALUE varchar2(255 char), SEQUENCE_NUMBER number(10,0), PARENT_FORMULA_ID number(10,0), primary key (ID))
create table FORMULA_DESC (ID number(10,0) not null, NAME varchar2(255 char) not null, POSITION number(10,0), PRIORITY_ID number(10,0), TEXT varchar2(255 char), DESC_ID number(10,0), primary key (ID))
create table FORMULA_PARAM_COMB (ID number(10,0) not null, DESCRIPTION varchar2(255 char) not null, NAME varchar2(255 char) not null, primary key (ID))
create table FORMULA_PARAM_COMB_ITEM (ID number(10,0) not null, DEFAULT_VALUE varchar2(255 char), OPTIONAL number(1,0), REPEATABLE number(1,0), SEQUENCE_NUMBER number(10,0), FORMULA_PARAM_COMB_ID number(10,0), DESC_ID number(10,0), NAME_ID number(10,0), primary key (ID))
create table FORMULA_PARAM_COMB_ITEM_TYPE (ID number(10,0) not null, FORMULA_RETURN_TYPE_ID number(10,0) not null, FORMULA_PARAM_COMB_ITEM_ID number(10,0), primary key (ID))
create table FORMULA_PARAM_COMB_ITEM_VALUE (id number(10,0) not null, value varchar2(255 char), DESC_ID number(10,0), FORMULA_PARAM_COMB_ITEM_ID number(10,0), primary key (id))
create table FORMULA_USAGE (id number(10,0) not null, name varchar2(255 char), DESC_ID number(10,0), FORMULA_DESC_ID number(10,0), FORMULA_PARAM_COMB_ID number(10,0), primary key (id))
create table FORMULA_USAGE_PARAM_COMB_ITEM (id number(10,0) not null, FORMULA_PARAM_COMB_ITEM_ID number(10,0), DESC_ID number(10,0), NAME_ID number(10,0), FORMULA_USAGE_ID number(10,0), primary key (id))
create table FORMULA_USAGE_RETURN_TYPE (ID number(10,0) not null, FORMULA_RETURN_TYPE_ID number(10,0) not null, FORMULA_USAGE_ID number(10,0), primary key (ID))
create table MULTILINGUAL_STRING (ID number(10,0) not null, NAME varchar2(255 char), primary key (ID))
create table MULTILINGUAL_STRING_ITEM (id number(10,0) not null, LANGUAGE varchar2(255 char) not null, TEXT varchar2(255 char), MULTILINGUAL_STRING_ID number(10,0), primary key (id))
create table RULE_DEFINITION (ID number(10,0) not null, RULE_IDENTITY_ID number(10,0), primary key (ID))
create table RULE_DEFINITION_PARAMETER (LEVEL_ID number(10,0) not null, RULE_DEFINITION_ID number(10,0) not null, VALUE varchar2(255 char) not null, primary key (LEVEL_ID, RULE_DEFINITION_ID, VALUE))
create table RULE_DESCRIPTION (ID number(10,0) not null, CODE varchar2(255 char), ALIAS_ID number(10,0), DESC_ID number(10,0), primary key (ID))
create table RULE_GROUP_ITEM (RULE_IDENTITY_ID number(10,0) not null, RULE_VERSION_ID number(10,0) not null, SEQUENCE_NUMBER number(10,0), primary key (RULE_IDENTITY_ID, RULE_VERSION_ID))
create table RULE_IDENTITY (id number(10,0) not null, primary key (id))
create table RULE_SET (ID number(10,0) not null, ALIAS_ID number(10,0), DESC_ID number(10,0), primary key (ID))
create table RULE_SET_VERSION (ID number(10,0) not null, AUTHOR_CREATION varchar2(255 char), DATE_CREATION timestamp, LABEL varchar2(255 char), AUTHOR_MODIFICATION varchar2(255 char), DATE_MODIFICATION timestamp, EXPLOITATION_START_DATE timestamp, STATUS number(10,0), VERSION varchar2(255 char), PARENT_ID number(10,0), RULE_SET_ID number(10,0), primary key (ID))
create table RULE_SET_VERSION_GROUP_ITEM (INCLUDING_RULE_SET_VERSION_ID number(10,0) not null, INCLUDED_RULE_SET_VERSION_ID number(10,0) not null, primary key (INCLUDING_RULE_SET_VERSION_ID, INCLUDED_RULE_SET_VERSION_ID))
create table RULE_SET_VERSION_RULE_VERSION (RULE_SET_VERSION_ID number(10,0) not null, RULE_VERSION_ID number(10,0) not null, primary key (RULE_SET_VERSION_ID, RULE_VERSION_ID))
create table RULE_VERSION (ID number(10,0) not null, EFFECTIVITY_END_DATE date, EFFECTIVITY_START_DATE date, ROUNDING_PRECISION double precision, ROUNDING_TYPE_ID number(10,0), RULE_TYPE number(10,0), VERSION varchar2(255 char), APPLICABILITY_CONDITION_ID number(10,0), FORMULA_ID number(10,0), RULE_DEFINITION_ID number(10,0), RULE_DESCRIPTION_ID number(10,0), primary key (ID))
alter table FORMULA add constraint UK_alffufkrxu54gw2wdd30oqyyl unique (PARENT_FORMULA_ID, SEQUENCE_NUMBER)
alter table RULE_GROUP_ITEM add constraint UK_m4qtcppj5uegr4xl2chusyc2e unique (RULE_VERSION_ID, SEQUENCE_NUMBER)
alter table FORMULA add constraint FK_5duebqvavvrf5tb1owb5uhbu7 foreign key (FORMULA_DESCRIPTION_ID) references FORMULA_DESC
alter table FORMULA add constraint FK_gvo4fpigpsm3d89nday4wtd97 foreign key (PARENT_FORMULA_ID) references FORMULA
alter table FORMULA_DESC add constraint FK_evs4m6o1r676157k8ie9obl8m foreign key (DESC_ID) references MULTILINGUAL_STRING
alter table FORMULA_PARAM_COMB_ITEM add constraint FK_kw1vsils1i3qewuutojsdv4xe foreign key (FORMULA_PARAM_COMB_ID) references FORMULA_PARAM_COMB
alter table FORMULA_PARAM_COMB_ITEM add constraint FK_aev96e8q19xcpm26e9ls37nw6 foreign key (DESC_ID) references MULTILINGUAL_STRING
alter table FORMULA_PARAM_COMB_ITEM add constraint FK_lvl04pwigojetxx42a4pn33dm foreign key (NAME_ID) references MULTILINGUAL_STRING
alter table FORMULA_PARAM_COMB_ITEM_TYPE add constraint FK_bcy9b8fafovgbjhrk9715xcqh foreign key (FORMULA_PARAM_COMB_ITEM_ID) references FORMULA_PARAM_COMB_ITEM
alter table FORMULA_PARAM_COMB_ITEM_VALUE add constraint FK_s4feegwjdgtw7ydvnsvg2j8le foreign key (DESC_ID) references MULTILINGUAL_STRING
alter table FORMULA_PARAM_COMB_ITEM_VALUE add constraint FK_48i2bt9il3qurcyrqgdb7mdlq foreign key (FORMULA_PARAM_COMB_ITEM_ID) references FORMULA_PARAM_COMB_ITEM
alter table FORMULA_USAGE add constraint FK_pi7fya10obe3a51p68jusqr6p foreign key (DESC_ID) references MULTILINGUAL_STRING
alter table FORMULA_USAGE add constraint FK_djpu20trblh9s9gul8vgmeqox foreign key (FORMULA_DESC_ID) references FORMULA_DESC
alter table FORMULA_USAGE add constraint FK_pko7o7jw7e3kh3mruokla7hb foreign key (FORMULA_PARAM_COMB_ID) references FORMULA_PARAM_COMB
alter table FORMULA_USAGE_PARAM_COMB_ITEM add constraint FK_12muum1vs39xw7qk9oin30hfc foreign key (FORMULA_PARAM_COMB_ITEM_ID) references FORMULA_PARAM_COMB_ITEM
alter table FORMULA_USAGE_PARAM_COMB_ITEM add constraint FK_mt1me0dbryoeh1d1n717nfaqo foreign key (DESC_ID) references MULTILINGUAL_STRING
alter table FORMULA_USAGE_PARAM_COMB_ITEM add constraint FK_nf2qqyss4s364lwuhlveitjub foreign key (NAME_ID) references MULTILINGUAL_STRING
alter table FORMULA_USAGE_PARAM_COMB_ITEM add constraint FK_fjtax73hilc88yv2i1feuau79 foreign key (FORMULA_USAGE_ID) references FORMULA_USAGE
alter table FORMULA_USAGE_RETURN_TYPE add constraint FK_j5j7qw5dg322ahxkbi506jj9o foreign key (FORMULA_USAGE_ID) references FORMULA_USAGE
alter table MULTILINGUAL_STRING_ITEM add constraint FK_byk7txxye0hkindrcyuq6bci6 foreign key (MULTILINGUAL_STRING_ID) references MULTILINGUAL_STRING
alter table RULE_DEFINITION add constraint FK_k7hrdkxpn1wwcgcnyk8dkhxcf foreign key (RULE_IDENTITY_ID) references RULE_IDENTITY
alter table RULE_DEFINITION_PARAMETER add constraint FK_h78fdggdxc2w29mi8qi64so9p foreign key (RULE_DEFINITION_ID) references RULE_DEFINITION
alter table RULE_DESCRIPTION add constraint FK_mmg8pe5r1xj4mwgp314gkof8h foreign key (ALIAS_ID) references MULTILINGUAL_STRING
alter table RULE_DESCRIPTION add constraint FK_sqhewrmaasig17uvxbhl6e7wg foreign key (DESC_ID) references MULTILINGUAL_STRING
alter table RULE_GROUP_ITEM add constraint FK_d6gv29tkdh4ub2s1rptbusgrf foreign key (RULE_VERSION_ID) references RULE_VERSION
alter table RULE_GROUP_ITEM add constraint FK_qges3e37h3m2ldiwalqerfvnv foreign key (RULE_IDENTITY_ID) references RULE_IDENTITY
alter table RULE_SET add constraint FK_2tsfiwgh2l1jge7rhbqx8fugr foreign key (ALIAS_ID) references MULTILINGUAL_STRING
alter table RULE_SET add constraint FK_42u542m4facgumt3602ksugvg foreign key (DESC_ID) references MULTILINGUAL_STRING
alter table RULE_SET_VERSION add constraint FK_bo1il7lt70cw1v4jgimgab6x6 foreign key (PARENT_ID) references RULE_SET_VERSION
alter table RULE_SET_VERSION add constraint FK_g07h81ceev6tlwr6p6119yluu foreign key (RULE_SET_ID) references RULE_SET
alter table RULE_SET_VERSION_GROUP_ITEM add constraint FK_lq0kuyu6y488f44i8st3r1opq foreign key (INCLUDED_RULE_SET_VERSION_ID) references RULE_SET_VERSION
alter table RULE_SET_VERSION_GROUP_ITEM add constraint FK_p0v12mgp6l707hr1r8idx9c0y foreign key (INCLUDING_RULE_SET_VERSION_ID) references RULE_SET_VERSION
alter table RULE_SET_VERSION_RULE_VERSION add constraint FK_ir7rb19hqiyil81v36bpew2by foreign key (RULE_VERSION_ID) references RULE_VERSION
alter table RULE_SET_VERSION_RULE_VERSION add constraint FK_cx4m0k44wb44ppp36n8g2tx01 foreign key (RULE_SET_VERSION_ID) references RULE_SET_VERSION
alter table RULE_VERSION add constraint FK_qcg8p6ms2w1mh3xqhk3w8jhfr foreign key (APPLICABILITY_CONDITION_ID) references FORMULA
alter table RULE_VERSION add constraint FK_9t8j22ln9mis82s0x5e4g086g foreign key (FORMULA_ID) references FORMULA
alter table RULE_VERSION add constraint FK_knc4amdpokrcjc7jgugu0266e foreign key (RULE_DEFINITION_ID) references RULE_DEFINITION
alter table RULE_VERSION add constraint FK_n5rx5r6gunv1dvy0vuig1da4g foreign key (RULE_DESCRIPTION_ID) references RULE_DESCRIPTION
create sequence SQ_TB_MULTILINGUAL_STRING_ID
create sequence SQ_TB_MULTILINGUAL_STR_ITEM_ID
