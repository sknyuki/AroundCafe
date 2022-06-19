from flask import redirect
import requests
import json

from ..config.OauthConfig import NaverConfig


class NaverOAuthService:
    def __init__(self):
        self.authorization_server = "https://nid.naver.com/%s"
        self.api_server = "https://openapi.naver.com/%s"
        self.CLIENT_ID = NaverConfig.CLIENT_ID
        self.REDIRECT_URI = NaverConfig.REDIRECT_URI
        self.CLIENT_SECRET = NaverConfig.CLIENT_SECRET
        self.STATE = NaverConfig.STATE

    def redirectLoginPage(self):
        return redirect(
            "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=%s&state=%s&redirect_uri=%s" % (
                self.CLIENT_ID, self.STATE, self.REDIRECT_URI)
        )

    def getAuth(self, code):
        url = "https://nid.naver.com/oauth2.0/token?grant_type=%s&client_id=%s&client_secret=%s&code=%s&state=%s" % (
            "authorization_code", self.CLIENT_ID, self.CLIENT_SECRET, code, self.STATE)

        response = requests.post(url=url)

        if response.status_code != 200:
            print("error")
        else:
            return response.json()

    def getUserInfo(self, access_token):
        # Get으로도 요청 가능하지만 Post가 더 안전
        url = self.api_server % "/v1/nid/me"
        headers = {
            "Authorization": "Bearer " + access_token,
        }
        response = requests.post(url=url, headers=headers).json()

        return response

    def post2MainServer(self, userinfo):
        socialNo = userinfo.get('response').get('id')
        email = userinfo.get('response').get('email')
        imageUrl = userinfo.get('response').get('profile_image')
        phoneNum = userinfo.get('response').get('mobile')
        birthyear = userinfo.get('response').get('birthyear')
        birthdayInit = userinfo.get('response').get('birthday')

        if birthyear == None:
            birthday = "0000-%s" % birthdayInit
        else:
            birthday = "{year}-{monthday}".format(
                year=birthyear, monthday=birthdayInit)

        headers = {
            "Content-Type": "application/json"
        }
        data = {
            "socialType": "NAVER",
            "socialNo": socialNo,
            "email": email,
            "birthday": birthday,
            "imageUrl": imageUrl,
            "phoneNum": phoneNum,
        }
        url = "http://localhost:7777/auth/oauth"
        response = requests.post(
            url=url, headers=headers, data=json.dumps(data)).json()
        return response
