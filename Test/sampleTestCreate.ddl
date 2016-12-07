create table FORMULA (FORMULA_DESCRIPTION_ID number(10,0) not null, ID number(10,0) not null, VALUE varchar2(255 char), SEQUENCE_NUMBER number(10,0), PARENT_FORMULA_ID number(10,0), primary key (ID))
create table FORMULA_DESCRIPTION (ID number(10,0) not null, DESCRIPTION_DE varchar2(255 char), DESCRIPTION_FR varchar2(255 char), DESCRIPTION_NL varchar2(255 char), DESCRIPTION_X varchar2(255 char), NAME varchar2(255 char) not null, PRIORITY_ID number(10,0), primary key (ID))
create table FORMULA_PARAMETER_COMBINATION (ID number(10,0) not null, FORMULA_RETURN_TYPE_ID number(10,0) not null, FORMULA_DESCRIPTION_ID number(10,0), primary key (ID))
create table FORMULA_PARAMETER_DESCRIPTION (ID number(10,0) not null, DESCRIPTION_DE varchar2(255 char), DESCRIPTION_FR varchar2(255 char), DESCRIPTION_NL varchar2(255 char), DESCRIPTION_X varchar2(255 char), NAME varchar2(255 char) not null, FORMULA_RETURN_TYPE_ID number(10,0) not null, SEQUENCE_NUMBER number(10,0) not null, PARAMETER_COMBINATION_ID number(10,0), primary key (ID))
create table RULE_DEFINITION (ID number(10,0) not null, RULE_IDENTITY_ID number(10,0), primary key (ID))
create table RULE_DEFINITION_PARAMETER (LEVEL_ID number(10,0) not null, RULE_DEFINITION_ID number(10,0) not null, VALUE varchar2(255 char) not null, primary key (LEVEL_ID, RULE_DEFINITION_ID, VALUE))
create table RULE_DESCRIPTION (ID number(10,0) not null, ALIAS_DE varchar2(255 char), ALIAS_FR varchar2(255 char), ALIAS_NL varchar2(255 char), ALIAS_X varchar2(255 char), CODE varchar2(255 char), DESCRIPTION_DE varchar2(255 char), DESCRIPTION_FR varchar2(255 char), DESCRIPTION_NL varchar2(255 char), DESCRIPTION_X varchar2(255 char), primary key (ID))
create table RULE_GROUP_ITEM (RULE_IDENTITY_ID number(10,0) not null, RULE_VERSION_ID number(10,0) not null, SEQUENCE_NUMBER number(10,0), primary key (RULE_IDENTITY_ID, RULE_VERSION_ID))
create table RULE_IDENTITY (id number(10,0) not null, primary key (id))
create table RULE_SET (ID number(10,0) not null, ALIAS_DE varchar2(255 char), ALIAS_FR varchar2(255 char), ALIAS_NL varchar2(255 char), ALIAS_X varchar2(255 char), DESCRIPTION_DE varchar2(255 char), DESCRIPTION_FR varchar2(255 char), DESCRIPTION_NL varchar2(255 char), DESCRIPTION_X varchar2(255 char), primary key (ID))
create table RULE_SET_VERSION (ID number(10,0) not null, AUTHOR_CREATION varchar2(255 char), DATE_CREATION timestamp, EXPLOITATION_START_DATE timestamp, LABEL varchar2(255 char), AUTHOR_MODIFICATION varchar2(255 char), DATE_MODIFICATION timestamp, STATUS number(10,0), VERSION varchar2(255 char), PARENT_ID number(10,0), RULE_SET_ID number(10,0), primary key (ID))
create table RULE_SET_VERSION_GROUP_ITEM (INCLUDING_RULE_SET_VERSION_ID number(10,0) not null, INCLUDED_RULE_SET_VERSION_ID number(10,0) not null, primary key (INCLUDING_RULE_SET_VERSION_ID, INCLUDED_RULE_SET_VERSION_ID))
create table RULE_SET_VERSION_RULE_VERSION (RULE_SET_VERSION_ID number(10,0) not null, RULE_VERSION_ID number(10,0) not null, primary key (RULE_SET_VERSION_ID, RULE_VERSION_ID))
create table RULE_VERSION (ID number(10,0) not null, EFFECTIVITY_END_DATE date, EFFECTIVITY_START_DATE date, ROUNDING_PRECISION double precision, ROUNDING_TYPE_ID number(10,0), RULE_TYPE number(10,0), VERSION varchar2(255 char), APPLICABILITY_CONDITION_ID number(10,0), FORMULA_ID number(10,0), RULE_DEFINITION_ID number(10,0), RULE_DESCRIPTION_ID number(10,0), primary key (ID))
alter table FORMULA add constraint UK_alffufkrxu54gw2wdd30oqyyl  unique (PARENT_FORMULA_ID, SEQUENCE_NUMBER)
alter table RULE_GROUP_ITEM add constraint UK_m4qtcppj5uegr4xl2chusyc2e  unique (RULE_VERSION_ID, SEQUENCE_NUMBER)
alter table FORMULA add constraint FK_5duebqvavvrf5tb1owb5uhbu7 foreign key (FORMULA_DESCRIPTION_ID) references FORMULA_DESCRIPTION
alter table FORMULA add constraint FK_gvo4fpigpsm3d89nday4wtd97 foreign key (PARENT_FORMULA_ID) references FORMULA
alter table FORMULA_PARAMETER_COMBINATION add constraint FK_lmk5nov7xxc6d6ttcdb5ghpue foreign key (FORMULA_DESCRIPTION_ID) references FORMULA_DESCRIPTION
alter table FORMULA_PARAMETER_DESCRIPTION add constraint FK_nhr1vbamqmtplgxxxyvlulqqp foreign key (PARAMETER_COMBINATION_ID) references FORMULA_PARAMETER_COMBINATION
alter table RULE_DEFINITION add constraint FK_k7hrdkxpn1wwcgcnyk8dkhxcf foreign key (RULE_IDENTITY_ID) references RULE_IDENTITY
alter table RULE_DEFINITION_PARAMETER add constraint FK_h78fdggdxc2w29mi8qi64so9p foreign key (RULE_DEFINITION_ID) references RULE_DEFINITION
alter table RULE_GROUP_ITEM add constraint FK_d6gv29tkdh4ub2s1rptbusgrf foreign key (RULE_VERSION_ID) references RULE_VERSION
alter table RULE_GROUP_ITEM add constraint FK_qges3e37h3m2ldiwalqerfvnv foreign key (RULE_IDENTITY_ID) references RULE_IDENTITY
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
