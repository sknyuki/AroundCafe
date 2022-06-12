import smtplib
import requests
from .MailConfig import *

from ..randomStringGenerator.RandomStringGenerator import RandomStringGenerator

from email.mime.text import MIMEText
#from email import encoders


class MailService:
    def __init__(self):
        self.SMTP_SERVER = SMTP_SERVER
        self.SSL_PORT = SSL_PORT
        self.TLS_PORT = TLS_PORT
        self.USER_ID = USER_ID
        self.USER_PASSWORD = USER_PASSWORD

    def send_email(self, msg):
        with smtplib.SMTP(self.SMTP_SERVER, self.TLS_PORT) as server:
            server.starttls()

            server.login(self.USER_ID, self.USER_PASSWORD)
            msg['From'] = self.USER_ID

            res = server.sendmail(msg['From'], msg["To"], msg.as_string())
            server.quit()

            if not res:
                print("Email Sent Complete")
            else:
                print(res)

    def verify_email(self, reciever):
        string = RandomStringGenerator.getRandomString(6)

        title = "Around Cafe 계정 확인 이메일"
        content = "입력하실 Code는 %s 입니다." % string

        msg = MIMEText(_text=content, _charset='utf-8')
        msg['Subject'] = title
        msg['To'] = reciever

        self.send_email(msg)

        return string
