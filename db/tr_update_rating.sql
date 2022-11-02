DELIMITER $$
CREATE TRIGGER msvc_applications.updateRatingApplication
AFTER INSERT ON msvc_applications.tbl_applications_ratings
FOR EACH ROW
BEGIN
  DECLARE NEW_RATING INTEGER;
  DECLARE RATING_CURSOR CURSOR FOR SELECT AVG(c.rating)
		FROM msvc_applications.tbl_applications_ratings a,
			msvc_applications.tbl_applications b,
			msvc_applications.tbl_ratings c
		WHERE a.application_id = b.id AND a.rating_id = c.id 
			AND a.application_id = NEW.application_id;
    
    OPEN RATING_CURSOR;
	FETCH RATING_CURSOR INTO NEW_RATING;
    CLOSE RATING_CURSOR;
                    
	UPDATE msvc_applications.tbl_applications 
		SET rating = NEW_RATING 
    WHERE id = NEW.application_id;
END$$
DELIMITER ;