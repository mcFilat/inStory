# Generated by Django 5.0.3 on 2024-03-11 21:34

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('inStoryBackend', '0001_initial'),
    ]

    operations = [
        migrations.RenameField(
            model_name='telegram',
            old_name='id',
            new_name='telegramid',
        ),
        migrations.RenameField(
            model_name='user',
            old_name='userpssword',
            new_name='userpassword',
        ),
    ]