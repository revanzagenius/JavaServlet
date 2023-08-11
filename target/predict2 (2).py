# -*- coding: utf-8 -*-
"""Predict.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1so846O5qDOyE4t_yoHuw0e88GvwToEdQ
"""

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

dataset = pd.read_csv("drug200.csv");

# Merubah String menjadi INT dengan Label Encoder
from sklearn import preprocessing
label_encoder = preprocessing.LabelEncoder()
dataset['Sex'] = label_encoder.fit_transform(dataset['Sex'])
dataset['Sex'].unique()

# Merubah String menjadi INT dengan Fungsi Encode
def encode(dataset):
  if dataset == 'LOW':
    return 0
  elif dataset == 'NORMAL':
    return 1
  else :
    return 2

dataset['BP'] = dataset['BP'].apply(encode)

from sklearn import preprocessing
label_encoder = preprocessing.LabelEncoder()
dataset['Cholesterol'] = label_encoder.fit_transform(dataset['Cholesterol'])
dataset['Cholesterol'].unique()

dataset['Drug'].replace({'DrugY':0,'drugX':1,'drugC':2, 'drugA':3, 'drugB':4},inplace=True)

dataset

X = dataset.iloc[:,:-1].values
y = dataset.iloc[:,-1].values

from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X,y, test_size=0.25, random_state=0)

# from sklearn.preprocessing import StandardScaler
# sc = StandardScaler()
# X_train = sc.fit_transform(X_train)
# X_test  = sc.fit_transform(X_test)

X_train

from sklearn.svm import SVC
lr = SVC(kernel= 'linear')
lr.fit(X_train, y_train)

new_instance = [41,	1,	0,	0,	7]  # input features for the new instance
prediction = lr.predict([new_instance])

# Print the predicted class label
print(prediction)

prediksi = lr.predict([[47,	1,	0,	0,	13.093]])[0]
prediksi

input_data = 47,	1,	0,	0,	13.093
id_np_array = np.asarray(input_data)
id_reshaped = id_np_array.reshape(1,-1)
predict = lr.predict(id_reshaped)

if(predict[0]==0):
    print("Tingkat stress anda adalah : Drug Y")
elif(predict[0]==1):
    print("Tingkat stress anda adalah : Drug X")
elif(predict[0]==2):
    print("Tingkat stress anda adalah : Drug C")
elif(predict[0]==3):
    print("Tingkat stress anda adalah : Drug A")
else:
    print("Tingkat stress anda adalah : Drug B")