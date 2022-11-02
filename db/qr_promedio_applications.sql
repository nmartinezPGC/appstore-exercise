select 
	AVG(c.rating)
from msvc_applications.tbl_applications_ratings a,
	msvc_applications.tbl_applications b,
    msvc_applications.tbl_ratings c
where a.application_id = b.id and a.rating_id = c.id 
	and a.application_id = 1;