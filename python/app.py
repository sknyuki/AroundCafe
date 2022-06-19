from oauth.OAuthController import oauthBp
from apis.mail.MailController import mailBp
from flask_cors import CORS
from flask import Flask


# app Instance 생성
app = Flask(__name__)
CORS(app)
# CORS 설정
# CORS(app, resources={
#     r'*': {'origins': ['http://localhost:7777', 'http://localhost:8080']}})

# Oauth Api import
app.register_blueprint(oauthBp, url_prefix='/oauth')
app.register_blueprint(mailBp, url_prefix='/mail')


# app.py로 실행시 app 실행
if __name__ == '__main__':
    app.run()
