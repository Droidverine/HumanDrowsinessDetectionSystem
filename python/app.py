import os
import pyrebase
from flask import *
app = Flask(__name__)
config = {
    "apiKey": "AIzaSyAkyScquIYYbKKbN2nFP0cVKuGQH-3tm_M",
    "authDomain": "hdds-e8fe9.firebaseapp.com",
    "databaseURL": "https://hdds-e8fe9.firebaseio.com",
    "projectId": "hdds-e8fe9",
    "storageBucket": "hdds-e8fe9.appspot.com",
    "messagingSenderId": "982447504454"
}

firebase = pyrebase.initialize_app(config)
auth = firebase.auth()
@app.route('/', methods=['GET', 'POST'])

def basic():
	unsuccessful = 'Please check your credentials'
	successful = 'Login successful'
	if request.method == 'POST':
		email = request.form['name']
		password = request.form['pass']
		try:
			auth.sign_in_with_email_and_password(email, password)
                        os.system('python pi_detect_drowsiness.py --cascade haarcascade_frontalface_default.xml --shape-predictor shape_predictor_68_face_landmarks.dat')
			return render_template('success.html', s=successful)
		except:
			return render_template('failed.html', us=unsuccessful)

	return render_template('Index.html')


if __name__ == '__main__':
     
        app.run()

    
