select ses.*
from user_detail ud
left join user_session ses on (ses.user_id = ud.id)
where ud.hashed_password ='%s'