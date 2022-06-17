import json
from urllib import response
from flask import Blueprint, jsonify, redirect, request
from flask_cors import cross_origin
from flask_restful import abort

from .naver.NaverOAuthService import NaverOAuthService
from .kakao.KakaoOAuthService import KakaoOauthService

oauthBp = Blueprint('oauthBp', __name__)


@oauthBp.route('/<string:socialType>/login', methods=['GET', 'POST'])
def redirectLoginPage(socialType):
    if(socialType == 'kakao'):
        return KakaoOauthService().redirectLoginPage()
    if(socialType == 'naver'):
        return NaverOAuthService().redirectLoginPage()


@oauthBp.route('/loginWithSNS', methods=['GET', 'POST'])
def getAuthfromServer():
    data = request.json
    socialType = data.get('socialType')
    code = data.get('code')
    try:
        if(socialType == 'kakao'):
            auth = KakaoOauthService().getAuth(code)
            # Auth값에서 accessToken 찾아옴
            access_token = auth.get("access_token")
            # accessToken을 통하여 user 정보 받아옴
            userinfo = KakaoOauthService().getUserInfo(access_token)
            # userinfo를 dto로 변환후 Spring Server로 Post
            token = KakaoOauthService().post2MainServer(userinfo)
            return token
        if(socialType == 'naver'):
            auth = NaverOAuthService().getAuth(code)
            # Auth값에서 AccessToken 찾아옴
            access_token = auth.get('access_token')
            # AccessToken을 통하여 user 정보 받아옴
            userinfo = NaverOAuthService().getUserInfo(access_token)
            token = NaverOAuthService().post2MainServer(userinfo)
            return token
    except Exception as e:
        return "Email is already used By other type of login. please login by different way", 400
