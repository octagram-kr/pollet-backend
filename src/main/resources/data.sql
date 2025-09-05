INSERT INTO `gifticon_product` (id, category, image_url, name, price, status, unlock_grade) VALUES (1,'food',NULL,'따뜻한 아이스 아메리카노',2000,'SELL','BRONZE');

INSERT INTO `member` (id, created_at, updated_at, attendance_streak, attended_at, auth_provider, email, gender, is_deleted, job, member_id, nickname, phone_number, profile_image_url, `rank`, role, status, year_of_birth) VALUES (1,'2025-09-03 11:18:39.149000','2025-09-03 11:19:25.944000',1,'2025-09-03 11:19:28.805000','KAKAO','polletadmin@gmail.com',NULL,_binary '\0',NULL,'polletadmin','Pollet Admin','01012345678','S3URL','BRONZE','ADMIN','ACTIVE','2000');
INSERT INTO `member` (id, created_at, updated_at, attendance_streak, attended_at, auth_provider, email, gender, is_deleted, job, member_id, nickname, phone_number, profile_image_url, `rank`, role, status, year_of_birth) VALUES (2,'2025-09-03 11:21:53.038000','2025-09-03 11:21:55.744000',1,'2025-09-03 11:21:58.183000','KAKAO','polletmember@gmail.com',NULL,_binary '\0',NULL,'polletmember','Pollet Member','01056781234','S3URL','BRONZE','MEMBER','ACTIVE','2000');

INSERT INTO `survey` (id, available_point, cover_url, creator_name, current_response_count, description, end_condition, end_date_time, estimated_time, is_template, primary_color, privacy_contents, privacy_expire_date, privacy_purpose_type, privacy_purpose_value, privacy_type, purpose, require_response_count, response_expire_date, reward_point, reward_type, secondary_color, start_date_time, subtitle, title, reward_gifticon_product_id, member_id) VALUES (1,10000,NULL,'Team Octagram',0,'테스트용 설문조사 (기프티콘)','TYPE_1','2025-09-15 09:00:00.830000',3,_binary '\0','',0,'2025-10-15 00:00:00.213000',0,NULL,'TYPE_1','테스트용',5,'2025-10-15 00:00:00.510000',NULL,'GIFTICON',NULL,'2025-09-03 09:00:00.892000','테스트용 샘플 설문조사 (기프티콘)','테스트 설문조사 (기프티콘)',1,1);
INSERT INTO `survey` (id, available_point, cover_url, creator_name, current_response_count, description, end_condition, end_date_time, estimated_time, is_template, primary_color, privacy_contents, privacy_expire_date, privacy_purpose_type, privacy_purpose_value, privacy_type, purpose, require_response_count, response_expire_date, reward_point, reward_type, secondary_color, start_date_time, subtitle, title, reward_gifticon_product_id, member_id) VALUES (2,10000,NULL,'Team Octagram',0,'테스트용 설문조사 (포인트)','TYPE_1','2025-09-15 00:00:00.817000',3,_binary '\0',NULL,0,'2025-10-15 00:00:00.095000',0,NULL,'TYPE_1','테스트용',5,'2025-10-15 00:00:00.722000',2000,'POINT',NULL,'2025-09-03 00:00:00.075000','테스트용 샘플 설문조사 (포인트)','테스트 설문조사 (포인트)',NULL,1);

INSERT INTO `question` (id, description, image_url, is_check_diligent, is_required, `order`, page, title, type, survey_id) VALUES (1,NULL,NULL,_binary '\0',_binary '\0',1,1,'기프티콘을 주로 어떤 경로로 얻게 되시나요?','SINGLE_CHOICE',1);
INSERT INTO `question` (id, description, image_url, is_check_diligent, is_required, `order`, page, title, type, survey_id) VALUES (2,NULL,NULL,_binary '\0',_binary '\0',2,1,'기프티콘을 사용할 때 불편하다고 느끼는 점을 모두 선택해주세요.','MULTIPLE_CHOICE',1);
INSERT INTO `question` (id, description, image_url, is_check_diligent, is_required, `order`, page, title, type, survey_id) VALUES (3,NULL,NULL,_binary '\0',_binary '\0',3,1,'한 달에 평균 몇 개의 기프티콘을 사용하시나요?','SHORT_ANSWER',1);
INSERT INTO `question` (id, description, image_url, is_check_diligent, is_required, `order`, page, title, type, survey_id) VALUES (4,NULL,NULL,_binary '\0',_binary '\0',4,1,'가장 선호하는 기프티콘 브랜드나 종류는 무엇이며, 그 이유는 무엇인가요?','LONG_ANSWER',1);
INSERT INTO `question` (id, description, image_url, is_check_diligent, is_required, `order`, page, title, type, survey_id) VALUES (5,NULL,NULL,_binary '\0',_binary '\0',1,1,'포인트를 사용하는 가장 주된 이유는 무엇입니까?','SINGLE_CHOICE',2);
INSERT INTO `question` (id, description, image_url, is_check_diligent, is_required, `order`, page, title, type, survey_id) VALUES (6,NULL,NULL,_binary '\0',_binary '\0',2,1,'현재 이용하시는 포인트 제도에 만족하는 점을 모두 선택해 주세요.','MULTIPLE_CHOICE',2);
INSERT INTO `question` (id, description, image_url, is_check_diligent, is_required, `order`, page, title, type, survey_id) VALUES (7,'예: 사용처 확대, 적립률 상승 등',NULL,_binary '\0',_binary '\0',3,1,'앞으로 포인트 제도가 어떻게 변화하면 더 자주 이용할 것 같으신가요?','SHORT_ANSWER',2);
INSERT INTO `question` (id, description, image_url, is_check_diligent, is_required, `order`, page, title, type, survey_id) VALUES (8,NULL,NULL,_binary '\0',_binary '\0',4,1,'포인트 사용 시 불편했던 경험이나 개선되었으면 하는 점이 있다면 자유롭게 작성해 주세요.','LONG_ANSWER',2);

INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (1,'다른 사람에게 선물 받음',NULL,1,1);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (2,'이벤트 경품으로 받음',NULL,2,1);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (3,'필요해서 직접 구매함',NULL,3,1);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (4,'기업/브랜드 프로모션으로 받음',NULL,4,1);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (5,'사용 가능한 매장이 제한적임',NULL,1,2);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (6,'유효기간이 짧아 사용 시기를 놓침',NULL,2,2);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (7,'상품 가격보다 낮은 금액의 기프티콘 사용 시 잔액 환불이 어려움',NULL,3,2);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (8,'사용 절차가 복잡하거나 번거로움',NULL,4,2);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (9,'현금처럼 사용 가능해서',NULL,1,5);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (10,'포인트로만 구매 가능한 상품이 있어서',NULL,2,5);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (11,'유효기간이 곧 만료될 예정이라서',NULL,3,5);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (12,'추가 할인 혜택을 받기 위해서',NULL,4,5);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (13,'다양한 사용처',NULL,1,6);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (14,'쉬운 적립 방법',NULL,2,6);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (15,'높은 적립률',NULL,3,6);
INSERT INTO `question_option` (id, content, image_url, `order`, question_id) VALUES (16,'포인트 관련 유용한 이벤트 및 프로모션',NULL,4,6);

INSERT INTO `survey_response` (id, completed_at, started_at, member_id, survey_id) VALUES (1,'2025-09-03 14:00:59.637000','2025-09-03 13:56:50.878000',1,1);
INSERT INTO `survey_response` (id, completed_at, started_at, member_id, survey_id) VALUES (2,'2025-09-03 14:01:29.400000','2025-09-03 13:57:20.466000',2,2);

INSERT INTO `question_response` (id, answer, question_id, survey_response_id) VALUES (1,NULL,1,1);
INSERT INTO `question_response` (id, answer, question_id, survey_response_id) VALUES (2,NULL,2,1);
INSERT INTO `question_response` (id, answer, question_id, survey_response_id) VALUES (3,'2',3,1);
INSERT INTO `question_response` (id, answer, question_id, survey_response_id) VALUES (4,'금액형 기프티콘을 가장 선호합니다. 특정 제품 교환권은 다른 메뉴로 바꾸기 어렵거나 추가 금액을 내야 하는 경우가 많아서, 원하는 메뉴를 자유롭게 선택할 수 있는 금액형이 훨씬 실용적이라고 생각합니다.',4,1);
INSERT INTO `question_response` (id, answer, question_id, survey_response_id) VALUES (5,NULL,5,2);
INSERT INTO `question_response` (id, answer, question_id, survey_response_id) VALUES (6,NULL,6,2);
INSERT INTO `question_response` (id, answer, question_id, survey_response_id) VALUES (7,'포인트 소멸되기 전에 알림을 더 자주 보내주면 좋겠습니다.',7,2);
INSERT INTO `question_response` (id, answer, question_id, survey_response_id) VALUES (8,'포인트를 쌓아두고 잊어버리는 경우가 많은데, 유효기간이 너무 짧아서 사용하지 못하고 소멸되는 포인트가 많아 아깝습니다. 유효기간을 더 늘려주거나 자동 갱신 제도가 있었으면 합니다.',8,2);

INSERT INTO `question_option_response` (id, question_option_id, question_response_id) VALUES (1, 1, 1);
INSERT INTO `question_option_response` (id, question_option_id, question_response_id) VALUES (2, 5, 2);
INSERT INTO `question_option_response` (id, question_option_id, question_response_id) VALUES (3, 7, 2);
INSERT INTO `question_option_response` (id, question_option_id, question_response_id) VALUES (4, 12, 5);
INSERT INTO `question_option_response` (id, question_option_id, question_response_id) VALUES (5, 14, 6);
INSERT INTO `question_option_response` (id, question_option_id, question_response_id) VALUES (6, 15, 6);

INSERT INTO `tag` (id, name, parent_tag_id) VALUES (1,'AI',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (2,'교육',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (3,'금융',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (4,'헬스케어',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (5,'여행',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (6,'패션뷰티',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (7,'커머스',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (8,'라이프스타일',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (9,'엔터테인먼트',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (10,'웰빙',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (11,'식품',NULL);
INSERT INTO `tag` (id, name, parent_tag_id) VALUES (12,'반려동물',NULL);

INSERT INTO `survey_tag` (id, survey_id, tag_id) VALUES (1,1,1);
INSERT INTO `survey_tag` (id, survey_id, tag_id) VALUES (2,1,2);
INSERT INTO `survey_tag` (id, survey_id, tag_id) VALUES (3,1,3);
INSERT INTO `survey_tag` (id, survey_id, tag_id) VALUES (4,2,4);
INSERT INTO `survey_tag` (id, survey_id, tag_id) VALUES (5,2,5);
INSERT INTO `survey_tag` (id, survey_id, tag_id) VALUES (6,2,6);