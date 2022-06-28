from flask import jsonify, redirect, request
import requests
import json

from ..config.OauthConfig import KakaoConfig
from ..utils.SaveImage import SaveImage


class KakaoOauthService:
    def __init__(self):
        self.authorization_server = "https://kauth.kakao.com/%s"
        self.api_server = "https://kapi.kakao.com/%s"
        self.CLIENT_ID = KakaoConfig.CLIENT_ID
        self.REDIRECT_URI = KakaoConfig.REDIRECT_URI
        self.CLIENT_SECRET = KakaoConfig.CLIENT_SECRET

    def redirectLoginPage(self):
        return redirect(
            "https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code" % (
                self.CLIENT_ID, self.REDIRECT_URI)
        )
        # return jsonify(url="https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code" % (
        #    self.CLIENT_ID, self.REDIRECT_URI))

    def getAuth(self, code):
        url = self.authorization_server % "oauth/token"
        headers = {
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8"
        }
        data = {
            "grant_type": "authorization_code",
            "client_id": self.CLIENT_ID,
            "redirect_uri": self.REDIRECT_URI,
            "code": code,
            "client_secret": self.CLIENT_SECRET,
        }
        response = requests.post(url=url, headers=headers, data=data)

        if response.status_code != 200:
            print("error")
        else:
            return response.json()

    def getUserInfo(self, access_token):
        # Get으로도 요청 가능하지만 Post가 더 안전
        url = self.api_server % "v2/user/me"
        headers = {
            "Content-Type": "application/x-www-form-urlencoded;charset=utf-8",
            "Authorization": "Bearer " + access_token,
        }
        response = requests.post(url=url, headers=headers).json()

        return response

    def post2MainServer(self, userinfo):
        socialNo = userinfo.get('id')
        email = userinfo.get('kakao_account').get('email')
        
        if(userinfo.get('kakao_account').get('birthday') != None) :
            birthdayInit = userinfo.get('kakao_account').get('birthday')
            birthMonth = birthdayInit[0:2]
            birthDay = birthdayInit[2:4]
            birth = "1900-{month}-{day}".format(
                month=birthMonth, day=birthDay)
        else :
            birth = "1900-01-01"

        imageUrl = userinfo.get('kakao_account').get(
            'profile').get('profile_image_url')
        
        headers = {
            "Content-Type": "application/json"
        }
        data = {
            "socialType": "KAKAO",
            "socialNo": socialNo,
            "email": email,
            "birthday": birth,
            "imageUrl": imageUrl,
        }
        url = "http://localhost:7777/auth/oauth"
        response = requests.post(
            url=url, headers=headers, data=json.dumps(data)).json()

        return response
