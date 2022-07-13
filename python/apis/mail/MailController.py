from flask import Blueprint, jsonify, request
from .MailService import MailService

mailBp = Blueprint('mailbp', __name__)


@mailBp.route("/verifyEmail", methods=['POST'])
def verifyEmail():
    #data = json.loads(request.data)
    # formType 가정
    email = request.json.get('email')
    code = MailService().verify_email(email)
    data = {'code': code}

    return jsonify(data)


@mailBp.route("/sendInfo", methods=['POST'])
def sendInfo():
    info = request.json
    MailService().send_payment_status(info)

    return 200
