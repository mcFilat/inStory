from django.db import models

class User(models.Model):
    id = models.AutoField(primary_key=True)
    username = models.CharField(max_length=100)
    userpassword = models.CharField(max_length=100)
    surname = models.CharField(max_length=100)
    middlename = models.CharField(max_length=100)
    phone = models.CharField(max_length=100)
    email = models.EmailField()

class Album(models.Model):
    albumid = models.AutoField(primary_key=True)
    albumcreatorid = models.IntegerField()
    albumname = models.CharField(max_length=100)
    participants = models.ManyToManyField(User, through='AlbumList', related_name='albums')

class AlbumList(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    album = models.ForeignKey(Album, on_delete=models.CASCADE)

class Telegram(models.Model):
    telegramid = models.AutoField(primary_key=True)
    albumid = models.CharField(max_length=100)
    sender = models.ForeignKey(User, on_delete=models.CASCADE)
    date = models.DateField()
    location = models.CharField(max_length=100)
    videolink = models.CharField(max_length=100)

class FriendsList(models.Model):
    person_a = models.ForeignKey(User, related_name='person_a', on_delete=models.CASCADE)
    person_b = models.ForeignKey(User, related_name='person_b', on_delete=models.CASCADE)
