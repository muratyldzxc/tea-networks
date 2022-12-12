# Tea Networks

Uygulamayı RESTAPI ve MySQL olmak üzere docker-compose up komutu ile ayağa kaldırabilirsiniz.
Tek yapmanız gereken projenin olduğu klasöre gelip terminale docker-compose up yazmanız.

API ile ilgili Swagger UI yapılarak dokümante edilmeye çalışılmıştır.
API ile ilgili postman istek koleksiyonu paylaşılmıştır.

# Notlar:
  Default olarak uygulama başladığında username: admin1 password: passwordadmin1 diye bir kullanıcı oluşturulmaktadır.
  Default olarak uygulama başladığında ROLE_USER ve ROLE_ADMIN rolleri rol tablosuna eklenmektedir.
  Eğer birden fazla kez uygulamayı başlatırsanız databasedeki sırasıyla user_roles , user , role tablolarını silmeyi unutmayınız.
  Uygulama başlarken user_roles , user , role tablolarına default olarak kayıt attığı için databasede kayıt varsa hata verecektir. 
  
