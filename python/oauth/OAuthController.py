from flask import Blueprint, jsonify, request

from .naver.NaverOAuthService import NaverOAuthService
from .kakao.KakaoOAuthService import KakaoOauthService

oauthBp = Blueprint('oauthBp', __name__)


@oauthBp.route('/<string:name>/login', methods=['GET'])
def redirectLoginPage(name):
    if(name == 'kakao'):
        return KakaoOauthService().redirectLoginPage()
    if(name == 'naver'):
        return NaverOAuthService().redirectLoginPage()


@oauthBp.route('/<string:name>/callback', methods=['GET', 'POST'])
def getAuthfromServer(name):
    code = request.args.get("code")
    if(name == 'kakao'):
        auth = KakaoOauthService().getAuth(code)
        # Auth값에서 accessToken 찾아옴
        access_token = auth.get("access_token")
        # accessToken을 통하여 user 정보 받아옴
        userinfo = KakaoOauthService().getUserInfo(access_token)
        # userinfo를 dto로 변환후 Spring Server로 Post
        token = KakaoOauthService().post2MainServer(userinfo)
        return jsonify(token)
    if(name == 'naver'):
        auth = NaverOAuthService().getAuth(code)
        # Auth값에서 AccessToken 찾아옴
        access_token = auth.get('access_token')
        # AccessToken을 통하여 user 정보 받아옴
        userinfo = NaverOAuthService().getUserInfo(access_token)
        token = NaverOAuthService().post2MainServer(userinfo)
        return jsonify(token)


@oauthBp.route('/', methods=['GET'])
def hello():
    return "hello"
