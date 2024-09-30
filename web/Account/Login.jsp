<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <title>
            Login Page
        </title>
        <script src="https://cdn.tailwindcss.com">
        </script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet"/>
        <style>
            body {
                background: linear-gradient(135deg, #6B73FF 0%, #000DFF 100%);
            }
        </style>
    </head>
    <body class="flex items-center justify-center min-h-screen">
        <div class="bg-white rounded-lg shadow-lg p-10 flex items-center">
            <div class="flex-1 flex justify-center">
                <div class="bg-gray-100 rounded-full p-10">
                    <img alt="User icon on a laptop screen" class="w-24 h-24" height="100" src="https://oaidalleapiprodscus.blob.core.windows.net/private/org-RcpoXHkzChYnDbFAyeQ8tamr/user-ehrvabJ3DufsCu8YJ7PqY5gl/img-yyXMEyqM2rRSJwr0rIFzjOs9.png?st=2024-09-30T13%3A15%3A41Z&amp;se=2024-09-30T15%3A15%3A41Z&amp;sp=r&amp;sv=2024-08-04&amp;sr=b&amp;rscd=inline&amp;rsct=image/png&amp;skoid=d505667d-d6c1-4a0a-bac7-5c84a87759f8&amp;sktid=a48cca56-e6da-484e-a814-9c849652bcb3&amp;skt=2024-09-30T01%3A37%3A42Z&amp;ske=2024-10-01T01%3A37%3A42Z&amp;sks=b&amp;skv=2024-08-04&amp;sig=bm9SrvSt9dNzbgZcCtz7Ggi/fpfdyqBhHONU%2BfHDuuM%3D" width="100"/>
                </div>
            </div>
            <div class="flex-1">
                <h2 class="text-2xl font-bold mb-6">
                    Member Login
                </h2>
                <form>
                    <div class="mb-4">
                        <input class="w-full px-4 py-2 border rounded-full focus:outline-none focus:ring-2 focus:ring-blue-600" placeholder="Email" type="email"/>
                    </div>
                    <div class="mb-4">
                        <input class="w-full px-4 py-2 border rounded-full focus:outline-none focus:ring-2 focus:ring-blue-600" placeholder="Password" type="password"/>
                    </div>
                    <div class="mb-4">
                        <button class="w-full bg-green-500 text-white py-2 rounded-full hover:bg-green-600" type="submit">
                            LOGIN
                        </button>
                    </div>
                    <div class="text-center mb-4">
                        <a class="text-gray-500" href="#">
                            Forgot Username / Password?
                        </a>
                    </div>
                    <div class="text-center">
                        <a class="text-gray-500" href="#">
                            Create your Account ?
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
