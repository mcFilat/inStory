# Generated by Django 5.0.3 on 2024-03-11 21:36

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('inStoryBackend', '0002_rename_id_telegram_telegramid_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='album',
            name='albumcreatorid',
            field=models.IntegerField(),
        ),
    ]
