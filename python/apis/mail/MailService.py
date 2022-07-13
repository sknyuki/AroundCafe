import smtplib

from flask import render_template
from .MailConfig import *

from .MailRepository import MailRepository
from ..randomStringGenerator.RandomStringGenerator import RandomStringGenerator

from email.mime.text import MIMEText


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

    def verify_email(self, receiver):
        string = RandomStringGenerator.getRandomString(6)

        title = "[Around Cafe] 인증코드 안내"
        content = """
            <!doctype html><html><head></head>
            <body>
                <div style="font-size: 18px; font-weight: 700; margin-bottom: 10px; margin-top: 60px;">
                인증코드를 확인해주세요.
                </div>
            <span style="font-size: 18px; font-weight: 700; margin-bottom: 10px; margin-top: 60px;">%s</span>
            <div style="margin-top: 60px; margin-bottom: 40px; line-height: 28px;">
                <div style="display: inline-block;">이메일 인증 절차에 따라 이메일 인증코드를 </div> 
                <div style="display: inline-block;"> 발급해드립니다.</div> 
                <div style="display: inline-block;">인증코드는 이메일 발송</div>
                <div style="display: inline-block;">시점으로부터 3분동안 유효합니다.</div>
                </div>
            </body></html>
        """ % string

        msg = MIMEText(content, 'html')
        msg['Subject'] = title
        msg['To'] = receiver

        self.send_email(msg)

        return string

    def send_payment_status(self, info):
        print(info)
        role = info.get('role')  # Role
        cafe_no = info.get('cafe_no')  # Nickname
        mem_no = info.get('mem_no')  # 받는사람 Cafe-Member Email
        status = info.get('status')  # 바꿀 status

        if(role == 'USER'):
            memNick = MailRepository().findMemNickByMemNo(mem_no)
            receiver = MailRepository().findEmailByCafeNo(cafe_no)

            title = "[Around Cafe] 주문 확인 메일"
            content = """
            %s님의 %s 요청이 들어왔습니다. 확인 바랍니다.
            """ % (memNick, status)

        elif(role == 'CAFE'):
            cafeName = MailRepository().findCafeNameByCafeNo(cafe_no)
            memNick = MailRepository().findMemNickByMemNo(mem_no)
            receiver = MailRepository().findEmailByMemNo(mem_no)

            if(status == 'CAFE_READY'):
                title = "[Around Cafe] 주문 확인 메일"
                content = """
                %s님이 %s에 주문하신 음료 준비가 시작되었습니다.
                """ % (memNick, cafeName)

            elif(status == 'PICK_UP_FINISHED'):
                title = "[Around Cafe] 주문 확인 메일"
                content = """
                %s님이 %s에 주문하신 음료가 완료되었습니다.
                """ % (memNick, cafeName)

        msg = MIMEText(content, 'html')
        msg['Subject'] = title
        msg['To'] = receiver

        self.send_email(msg)
