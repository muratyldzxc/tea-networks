# Tea Networks

<br /> <br /> ** Uygulamayı RESTAPI ve MySQL olmak üzere docker-compose up komutu ile ayağa kaldırabilirsiniz.
<br /> <br /> ** Tek yapmanız gereken projenin olduğu klasöre gelip terminale docker-compose up yazmanız.

<br /> <br /> * API ile ilgili Swagger UI yapılarak dokümante edilmeye çalışılmıştır.
<br /> <br /> * API ile ilgili postman istek koleksiyonu 'Tea Networks Task.postman_collection.json' isimli dosya ile ana dizinde paylaşılmıştır.

# Notlar:
 <br /> <br /> * Default olarak uygulama başladığında username: admin1 password: passwordadmin1 diye bir kullanıcı oluşturulmaktadır.
 <br /> <br /> * Default olarak uygulama başladığında ROLE_USER ve ROLE_ADMIN rolleri rol tablosuna eklenmektedir.
 <br /> <br /> * Eğer birden fazla kez uygulamayı başlatırsanız databasedeki sırasıyla user_roles , user , role tablolarını silmeyi unutmayınız.
 <br /> <br /> * Uygulama başlarken user_roles , user , role tablolarına default olarak kayıt attığı için databasede kayıt varsa hata verecektir. 
  
