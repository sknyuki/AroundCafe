from oauth.OAuthController import oauthBp
from flask_cors import CORS, cross_origin
from flask import Flask, url_for, request, jsonify, redirect, Response, Blueprint
#import sys
#import os
# sys.path.append(os.path.dirname(os.path.abspath(__file__)))


# app Instance 생성
app = Flask(__name__)

# Oauth Api import
app.register_blueprint(oauthBp, url_prefix='/oauth')

# CORS 설정
CORS(app)

# app.py로 실행시 app 실행
if __name__ == '__main__':
    app.run()
